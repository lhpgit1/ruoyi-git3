<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="学号" prop="lateStuId">
        <el-input
          v-model="queryParams.lateStuId"
          placeholder="请输入学号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="姓名" prop="lateStuName">
        <el-input
          v-model="queryParams.lateStuName"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="夜归原由" prop="lateReason">
        <el-input
          v-model="queryParams.lateReason"
          placeholder="请输入夜归原由"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="楼栋名" prop="lateDeptName">
        <el-input
          v-model="queryParams.lateDeptName"
          placeholder="请输入楼栋名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="宿舍号" prop="lateRoomId">
        <el-input
          v-model="queryParams.lateRoomId"
          placeholder="请输入宿舍号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="夜归日期" prop="lateDate">
        <el-date-picker clearable
          v-model="queryParams.lateDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择夜归日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:late:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:late:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:late:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:late:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="lateList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="夜归记录号" align="center" prop="lateId" />
      <el-table-column label="学号" align="center" prop="lateStuId" />
      <el-table-column label="姓名" align="center" prop="lateStuName" />
      <el-table-column label="夜归原由" align="center" prop="lateReason" />
      <el-table-column label="楼栋名" align="center" prop="lateDeptName" />
      <el-table-column label="宿舍号" align="center" prop="lateRoomId" />
      <el-table-column label="夜归日期" align="center" prop="lateDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lateDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:late:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:late:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改夜归管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学号" prop="lateStuId">
          <el-input v-model="form.lateStuId" placeholder="请输入学号" />
        </el-form-item>
        <el-form-item label="姓名" prop="lateStuName">
          <el-input v-model="form.lateStuName" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="夜归原由" prop="lateReason">
          <el-input v-model="form.lateReason" placeholder="请输入夜归原由" />
        </el-form-item>
        <el-form-item label="楼栋名" prop="lateDeptName">
          <el-input v-model="form.lateDeptName" placeholder="请输入楼栋名" />
        </el-form-item>
        <el-form-item label="宿舍号" prop="lateRoomId">
          <el-input v-model="form.lateRoomId" placeholder="请输入宿舍号" />
        </el-form-item>
        <el-form-item label="夜归日期" prop="lateDate">
          <el-date-picker clearable
            v-model="form.lateDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择夜归日期">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listLate, getLate, delLate, addLate, updateLate } from "@/api/system/late";

export default {
  name: "Late",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 夜归管理表格数据
      lateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        lateStuId: null,
        lateStuName: null,
        lateReason: null,
        lateDeptName: null,
        lateRoomId: null,
        lateDate: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        lateStuId: [
          { required: true, message: "学号不能为空", trigger: "blur" }
        ],
        lateStuName: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        lateReason: [
          { required: true, message: "夜归原由不能为空", trigger: "blur" }
        ],
        lateDeptName: [
          { required: true, message: "楼栋名不能为空", trigger: "blur" }
        ],
        lateRoomId: [
          { required: true, message: "宿舍号不能为空", trigger: "blur" }
        ],
        lateDate: [
          { required: true, message: "夜归日期不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询夜归管理列表 */
    getList() {
      this.loading = true;
      listLate(this.queryParams).then(response => {
        this.lateList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        lateId: null,
        lateStuId: null,
        lateStuName: null,
        lateReason: null,
        lateDeptName: null,
        lateRoomId: null,
        lateDate: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.lateId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加夜归管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const lateId = row.lateId || this.ids
      getLate(lateId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改夜归管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.lateId != null) {
            updateLate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addLate(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const lateIds = row.lateId || this.ids;
      this.$modal.confirm('是否确认删除夜归管理编号为"' + lateIds + '"的数据项？').then(function() {
        return delLate(lateIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/late/export', {
        ...this.queryParams
      }, `late_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
