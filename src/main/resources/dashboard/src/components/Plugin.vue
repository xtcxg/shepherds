<template>
    <div>
        <el-header>
            <el-button-group>
                <el-button @click="chose($event,'installed')">installed</el-button>
                <el-button @click="chose($event,'update')" >update</el-button>
                <el-button @click="chose($event,'search')" >search</el-button>
                <el-button @click="chose($event,'upload')" >upload</el-button>
            </el-button-group>
        </el-header>
        <el-main v-if="this.page==='installed'">
            <el-table :data="insertedData" border style="width: min-content">
                <el-table-column prop="jobIndex" label="id" width="150" />
                <el-table-column prop="jobName" label="名称" width="120" />
                <el-table-column prop="jobVersion" label="版本" width="120" />
                <el-table-column prop="obtainTime" label="录入时间" width="160" />
                <el-table-column  label="操作" width="100">
                    <template slot-scope="scope">
                        <el-button @click="jobDelete(scope.row)" type="text" size="small">删除</el-button>
                        <el-button @click="jobInspect(scope.row)" type="text" size="small">详情</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-main>
        <el-main v-else-if="this.page==='update'">
            update
        </el-main>
        <el-main v-else-if="this.page==='search'">
            search
        </el-main>
    </div>
</template>

<script>

    export default {
        name: "Plugin",
        data() {
            return {
                page: "installed",
                insertedData: [],
            }
        },
        methods: {
            chose(e,page) {
              this.page = page;
              if (page === "installed") {
                  this.insertedJobList(1,999);
              }
            },
            jobDelete(row){ //删除工作
                console.log(row)
            },
            jobInspect(row){    // 获取工作详情
                console.log(row)
            },
            insertedJobList(page,size){ // 获取已注册的全部工作
                this.$http.post(this.$store.state.url.jobs,{page: page, size: size})
                    .then((response) => {
                    this.insertedData = response.body;
                }, (err) => {
                    console.log(err);
                })
            }
        },
        mounted() {
            this.insertedJobList(1,999);
        }
    }
</script>

<style scoped>

</style>