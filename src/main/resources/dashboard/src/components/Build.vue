<template>
    <div class="build-main" style="height: 100%">
        <div v-if="this.page==='chose'">
            <el-button class="job" v-for="job in this.jobs" @click="build(job)">
                {{ job.jobName + "\n" + job.jobVersion }}
            </el-button>
        </div>
        <el-container v-else-if="page==='build'" style="height: 100%">
            <el-header class="admin-header" style="height: 40px;border-bottom: #ebb563 solid;">
                <el-col><el-button  class="build-home" v-on:click="backHome()"/></el-col>
            </el-header>
            <el-main v-if="this.content==='in'">
                <el-form ref="buildForm" :model="buildForm" :rules="buildRules" label-width="80px" >
                    <el-form-item label="name" prop="name">
                        <el-input placeholder="" v-model="buildForm.workerName" />
                    </el-form-item>
                    <el-form-item label="port">
                        <el-input v-model.number="buildForm.port"  maxlength="6" minlength="2"/>
                    </el-form-item>
                    <el-form-item label="启动命令" prop="cmd">
                        <el-input v-model="buildForm.cmd" aria-required="true"/>
                    </el-form-item>
                    <el-form-item label="启动参数" prop="args">
                        <el-input v-model="buildForm.args"/>
                    </el-form-item>
                    <el-form-item label="自动重启">
                        <el-switch v-model="buildForm.autoBoot"></el-switch>
                    </el-form-item>

                    <el-form-item label="next" class="item-next">
                        <span slot="label" style="font-size: 25px">next</span>
                        <el-button type="primary" @click="addWorker" icon="el-icon-right" circle />
                    </el-form-item>
                </el-form>
            </el-main>
            <el-main v-else-if="this.content==='out'" style="height: 100%">
                <iframe class="iframe" v-bind:src="this.pluginView" frameborder="0" width="100%" height="98%" id="ifr"/>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Build",
        data(){
            return {
                jobs:[],
                page: "chose",
                content: 'in',
                pluginView:"",
                job: {
                    jobCmd: "",
                    jobIndex: "",
                },
                buildForm: {
                    port: "",
                    jobIndex:"",
                    workerName:"",
                    autoBoot:false,
                    cmd:""
                },
                buildRules: {

                }
            }
        },
        methods: {
            jobList(page,size) {
                this.$http.post(this.$store.state.url.jobs,{page: page, size: size})
                    .then((response) => {
                        this.jobs = response.body;
                    }, (err) => {
                        console.log(err);
                    })
            },
            build(job) {
                this.content = 'in';
                this.job = job;
                this.buildForm.jobIndex = job.jobIndex;
                this.buildForm.cmd = job.jobCmd;
                this.page = "build";
            },
            backHome(){
                this.page = "chose";
                this.content = 'in';
            },
            addWorker() {
                let prop;
                if (""!==this.buildForm.port) {
                    prop = {
                        "server.port": this.buildForm.port + ""
                    }
                }
                let worker = {
                    workerName: this.buildForm.workerName,
                    prop: prop,
                    executePlan: this.buildForm.autoBoot ? {"autoBoot":"on"}:{"autoBoot":"off"},
                    jobIndex: this.buildForm.jobIndex,
                    cmd: this.buildForm.cmd,
                    args: this.buildForm.args,
                }

                this.content = 'out';
                this.pluginView = "./views/cont.html"

                // console.log(worker);
                // this.$http.post(this.$store.state.url.addWorker,worker)
                // .then((response)=>{
                //     console.log(response);
                // }, (err)=>{
                //     console.log(err);
                // })

                // this.$http.get("./views/cont.html").then((response) => {
                //     if (response.ok) {
                //         // this.pluginView = response.data;
                //         this.pluginView = "apple";
                //     } else {
                //         this.$notify.error({
                //             duration: 1500,
                //             title: response.status,
                //             message: response.statusText
                //         });
                //     }
                // }, (err)=>{
                //     this.$notify.error({
                //         duration: 1500,
                //         title: err.status,
                //         message: err.statusText
                //     });
                // })
            }
        },
        mounted() {
            this.jobList(1,999);
        }
    }
</script>

<style scoped>
    .build-home {
        height: 30px;
        width: 30px;
        padding: 0px;
        margin: 0;
        background: url("../image/home.png") center no-repeat;
        /*font-size: 26px;*/
        background-size: cover;
        border: rgba(234, 230, 230, 0);
    }
    .build-main {
        text-align: left;
    }
    .job {
        width: 150px;
        height: 150px;
        font-size: 1.5em;
        text-align: center;
        white-space: pre-line;
        line-height: 35px;
    }
</style>