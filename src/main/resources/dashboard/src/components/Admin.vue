<template>
   <div>
       <el-row class="admin-row">
           <el-col :span="4" class="admin-c1 admin-c">id</el-col>
           <el-col :span="4" class="admin-c2 admin-c">origin id</el-col>
           <el-col :span="4" class="admin-c3 admin-c">status</el-col>
           <el-col :span="6" class="admin-c">operation</el-col>
       </el-row>

       <el-row v-for="worker in workers" class="admin-row">
           <el-col :span="4" class="admin-c1 admin-c"> {{ worker.workerId }}</el-col>
           <el-col :span="4" class="admin-c2 admin-c"> {{ worker.jobIndex }}</el-col>
           <el-col :span="4" class="admin-c3 admin-c"> {{ worker.workerStatus }}</el-col>
           <el-col :span="2" class="admin-c4"><el-button type="warning" plain @click="restart(worker.workerId)">restart</el-button></el-col>
           <el-col :span="2" class="admin-c4"><el-button type="danger" plain @click="restart(worker.workerId)">stop</el-button></el-col>
           <el-col :span="2" class="admin-c4"><el-button type="primary" plain @click="restart(worker.workerId)">inspect</el-button></el-col>
       </el-row>
   </div>
</template>

<script>
    export default {
        name: "Admin",
        data(){
            return {
                workers:[],
            }
        },
        methods:{
            workList(page,size){
                this.$http.post(this.$store.state.url.workers,{page: page, size: size})
                    .then((response) => {
                        this.workers = response.body;
                    }, (err) => {
                        console.log(err);
                    })
            },
            restart(workerId) {
                console.log(workerId);
            }
        },
        mounted() {
            this.$nextTick(()=>{
                this.workList(1,999);
            })
        }
    }
</script>

<style scoped>
    .admin-c {
        font-size: 20px;
        padding: 10px 0;
        height: 42px;
    }
    .admin-c1 {
        background: #e5e9f2;
    }
    .admin-c2 {
        background-color: #b6bbc2;
    }
    .admin-c3 {
        background: #e5e9f2;
    }
    .admin-c4 {
        background-color: #f9fafc;
    }
    .admin-row {
        margin: 5px 0;
    }
</style>