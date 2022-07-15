<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="所属楼栋" prop="roomDept">
        <el-input
          v-model="queryParams.roomDept"
          placeholder="请输入所属楼栋"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="宿舍号" prop="roomNumber">
        <el-input
          v-model="queryParams.roomNumber"
          placeholder="请输入宿舍号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="宿舍长" prop="roomLeader">
        <el-input
          v-model="queryParams.roomLeader"
          placeholder="请输入宿舍长"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="roomPhoneNumber">
        <el-input
          v-model="queryParams.roomPhoneNumber"
          placeholder="请输入宿舍联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--      <el-form-item label="维修内容" prop="comment">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.comment"-->
      <!--          placeholder="请输入维修内容"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item label="申报日期" prop="repairDate">
        <el-date-picker clearable
                        v-model="queryParams.repairDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择申报日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_normal_disable1"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['system:repair:add']"
        >新增
        </el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['system:repair:edit']"-->
<!--        >修改-->
<!--        </el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:repair:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:repair:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="repairList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="维修Id" align="center" prop="repairId"/>
      <el-table-column label="所属楼栋" align="center" prop="roomDept"/>
      <el-table-column label="宿舍号" align="center" prop="roomNumber"/>
      <el-table-column label="宿舍长" align="center" prop="roomLeader"/>
      <el-table-column label="宿舍联系电话" align="center" prop="roomPhoneNumber"/>
      <!--      <el-table-column label="维修内容" align="center" prop="comment" />-->
      <el-table-column label="申报日期" align="center" prop="repairDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.repairDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal_disable1" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:repair:edit']"
          >详情
          </el-button>
          <!--          <el-button-->
          <!--            size="mini"-->
          <!--            type="text"-->
          <!--            icon="el-icon-delete"-->
          <!--            @click="handleDelete(scope.row)"-->
          <!--            v-hasPermi="['system:repair:remove']"-->
          <!--          >删除</el-button>-->
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

    <!-- 添加或修改1对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="所属楼栋" prop="roomDept">
          <el-input v-model="form.roomDept" placeholder="请输入所属楼栋" :disabled="edit"/>
        </el-form-item>
        <el-form-item label="宿舍号" prop="roomNumber">
          <el-input v-model="form.roomNumber" placeholder="请输入宿舍号" :disabled="edit"/>
        </el-form-item>
        <el-form-item label="宿舍长" prop="roomLeader">
          <el-input v-model="form.roomLeader" placeholder="请输入宿舍长" :disabled="edit"/>
        </el-form-item>
        <el-form-item label="联系电话" prop="roomPhoneNumber">
          <el-input v-model="form.roomPhoneNumber" placeholder="请输入宿舍联系电话" :disabled="edit"/>
        </el-form-item>
        <el-form-item label="维修内容" prop="comment">
          <el-input v-model="form.comment" placeholder="请输入维修内容" :disabled="edit"/>
        </el-form-item>
        <el-form-item label="申报日期" prop="repairDate">
          <el-date-picker clearable
                          v-model="form.repairDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择申报日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.sys_normal_disable1"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}
            </el-radio>
          </el-radio-group>
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
  import {listRepair, getRepair, delRepair, addRepair, updateRepair} from "@/api/system/repair";

  export default {
    name: "Repair",
    dicts: ['sys_normal_disable1'],
    data() {
      return {
        edit: true,
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
        // 1表格数据
        repairList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          roomDept: null,
          roomNumber: null,
          roomLeader: null,
          roomPhoneNumber: null,
          comment: null,
          repairDate: null,
          status: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {}
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询1列表 */
      getList() {
        this.loading = true;
        listRepair(this.queryParams).then(response => {
          this.repairList = response.rows;
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
          repairId: null,
          roomDept: null,
          roomNumber: null,
          roomLeader: null,
          roomPhoneNumber: null,
          comment: null,
          repairDate: null,
          status: "0"
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
        this.ids = selection.map(item => item.repairId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.edit = false;
        this.reset();
        this.open = true;
        this.title = "添加维修记录";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.edit = true;
        this.reset();
        const repairId = row.repairId || this.ids
        getRepair(repairId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "维修Id为"+repairId+"的详细信息";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.repairId != null) {
              updateRepair(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addRepair(this.form).then(response => {
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
        const repairIds = row.repairId || this.ids;
        this.$modal.confirm('是否确认删除1编号为"' + repairIds + '"的数据项？').then(function () {
          return delRepair(repairIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/repair/export', {
          ...this.queryParams
        }, `repair_${new Date().getTime()}.xlsx`)
      }
    }
  };
</script>
