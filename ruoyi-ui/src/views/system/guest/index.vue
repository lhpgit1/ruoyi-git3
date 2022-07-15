<template>
  <div class="app-container">
    <el-form :model="queryParams" sref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="80px">
      <el-form-item label="姓名"  prop="guestName" >
        <el-input
          v-model="queryParams.guestName"
          placeholder="请输入来访人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="guestPhoneNumber">
        <el-input
          v-model="queryParams.guestPhoneNumber"
          placeholder="请输入来访人手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="来访时间" prop="guestTime">
        <el-date-picker clearable
          v-model="queryParams.guestTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择来访时间记录">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="访问原由" prop="guestExplain">
        <el-input
          v-model="queryParams.guestExplain"
          placeholder="请输入访问原由说明"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="访问楼栋" prop="guestDept">
        <el-input
          v-model="queryParams.guestDept"
          placeholder="请输入访问去向楼栋"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="访问宿舍" prop="guestRoom">
        <el-input
          v-model="queryParams.guestRoom"
          placeholder="请输入访问去向宿舍号"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:guest:add']"
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
          v-hasPermi="['system:guest:edit']"
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
          v-hasPermi="['system:guest:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:guest:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="guestList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="来访记录号" align="center" prop="guestId" />
      <el-table-column label="来访人姓名" align="center" prop="guestName" />
      <el-table-column label="来访人手机号" align="center" prop="guestPhoneNumber" width="106"/>
      <el-table-column label="来访时间记录" align="center" prop="guestTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.guestTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="访问原由说明" align="center" prop="guestExplain" />
      <el-table-column label="访问去向楼栋" align="center" prop="guestDept" />
      <el-table-column label="访问去向宿舍号" align="center" prop="guestRoom" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:guest:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:guest:remove']"
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

    <!-- 添加或修改【请填写功能名称】对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="来访人姓名" prop="guestName">
          <el-input v-model="form.guestName" placeholder="请输入来访人姓名" />
        </el-form-item>
        <el-form-item label="来访人手机号" prop="guestPhoneNumber">
          <el-input v-model="form.guestPhoneNumber" placeholder="请输入来访人手机号" />
        </el-form-item>
        <el-form-item label="来访时间记录" prop="guestTime">
          <el-date-picker clearable
            v-model="form.guestTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择来访时间记录">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="访问原由说明" prop="guestExplain">
          <el-input v-model="form.guestExplain" placeholder="请输入访问原由说明" />
        </el-form-item>
        <el-form-item label="访问去向楼栋" prop="guestDept">
          <el-input v-model="form.guestDept" placeholder="请输入访问去向楼栋" />
        </el-form-item>
        <el-form-item label="访问去向宿舍号" prop="guestRoom">
          <el-input v-model="form.guestRoom" placeholder="请输入访问去向宿舍号" />
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
import { listGuest, getGuest, delGuest, addGuest, updateGuest } from "@/api/system/guest";

export default {
  name: "Guest",
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
      // 【请填写功能名称】表格数据
      guestList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        guestName: null,
        guestPhoneNumber: null,
        guestTime: null,
        guestExplain: null,
        guestDept: null,
        guestRoom: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询【请填写功能名称】列表 */
    getList() {
      this.loading = true;
      listGuest(this.queryParams).then(response => {
        this.guestList = response.rows;
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
        guestId: null,
        guestName: null,
        guestPhoneNumber: null,
        guestTime: null,
        guestExplain: null,
        guestDept: null,
        guestRoom: null
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
      this.ids = selection.map(item => item.guestId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加访问记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const guestId = row.guestId || this.ids
      getGuest(guestId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改访问记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.guestId != null) {
            updateGuest(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGuest(this.form).then(response => {
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
      const guestIds = row.guestId || this.ids;
      this.$modal.confirm('是否确认删除访问记录中编号为"' + guestIds + '"的数据项？').then(function() {
        return delGuest(guestIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/guest/export', {
        ...this.queryParams
      }, `guest_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
