<!--分页-->
<template>
  <el-pagination
    background
    :page-size.sync="page.size"
    :total="page.total"
    :current-page.sync="page.page"
    style="margin-top: 8px;padding-right:0;text-align: right"
    layout="total, prev, pager, next, sizes"
    @size-change="crud.sizeChangeHandler($event)"
    @current-change="crud.pageChangeHandler"
  />
</template>
<script>
import { pagination } from '@crud/crud'

export default {
  mixins: [pagination()]
}
</script>
<style scoped>
::v-deep .el-pagination__sizes {
  margin-right: 0;
}
</style>
