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
            <el-main v-if="this.content==='in'" style="max-width:800px">
                <el-form ref="buildForm" :model="buildForm" :rules="buildRules" label-width="80px" >
                    <el-form-item label="应用名称" prop="name">
                        <el-input placeholder="" v-model="buildForm.workerName" />
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
                    <div v-if="'table'===this.propType">
                        <el-row style="height: 30px">
                            <el-col :span="4" @click="propTypeChange">
                                <span @click="propTypeChange" style="font-size: 18px">应用配置信息</span>
                            </el-col>
                            <el-col :span="3">
                                <span style="font-size: 16px;color: #3a8ee6">改为文本</span><i class="el-icon-document" style="font-size: 18px"  @click="propTypeChange"/>
                            </el-col>
                            <el-col :span="2">
                                <i class="el-icon-circle-plus" @click="addProp" style="font-size: 24px"></i>
                            </el-col>
                        </el-row>
                        <el-row v-for="property in this.properties" style="margin-bottom: 3px">
                            <el-col :span="10">
                                <el-input v-model="property.key" />
                            </el-col>
                            <el-col :span="1" style="text-align: center;padding-top: 10px"> = </el-col>
                            <el-col :span="10">
                                <el-input v-model="property.val" />
                            </el-col>
                            <el-col :span="2" style="text-align: right">
                                <el-button type="danger" icon="el-icon-delete" @click="reduceProp(property)" circle/>
                            </el-col>
                        </el-row>
                    </div>
                    <div v-else-if="'text'===this.propType">
                        <el-row style="height: 30px">
                            <el-col :span="4">
                                <span style="font-size: 18px">应用配置信息</span>
                            </el-col>
                            <el-col :span="3">
                                <span style="font-size: 16px;color: #3a8ee6">改为键值</span><i class="el-icon-s-order" style="font-size: 18px"  @click="propTypeChange"/>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-input type="textarea" @input="onInput($event)" :value="this.propText" :autosize="{ minRows: 1}"></el-input>
                        </el-row>

                    </div>
                </el-form>
                <el-row>
                    <el-col :span=22 style="text-align: right">
                        <el-button type="primary" style="font-size: 16px;margin-top: 10px" @click="addWorker" round >next<i class="el-icon-right el-icon--right"></i></el-button>
                    </el-col>
                </el-row>
            </el-main>
            <el-main v-else-if="this.content==='out'" style="height: 100%;padding: 0">
                <iframe class="iframe" v-bind:src="this.pluginView" frameborder="0" width="100%" height="98%"/>
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
                    jobIndex:"",
                    workerName:"",
                    autoBoot:false,
                    cmd:""
                },
                buildRules: {

                },
                properties: [
                    {
                        key: "",
                        val: "",
                    }
                ],
                propType : "table",
                propText: "apple",
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
                let worker = {
                    workerName: this.buildForm.workerName,
                    executePlan: this.buildForm.autoBoot ? {"autoBoot":"on"}:{"autoBoot":"off"},
                    jobIndex: this.buildForm.jobIndex,
                    cmd: this.buildForm.cmd,
                    args: this.buildForm.args,
                    prop: ""
                }
                let prop = {};
                this.properties.forEach(e => {
                    prop[e.key] = e.val;
                });

                worker.prop = JSON.stringify(prop);
                console.log(worker);


                this.content = 'out';
                this.pluginView = "./views/shepherd/index.html"

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
            },
            addProp(){
                let flag = true;
                this.properties.forEach(e => {
                    if (e.key===""){
                        this.$message({message:"can't do it",type:"warning",duration:1500});
                        flag = false;
                    }
                })
                if (flag) {
                    this.properties.push({key:"",val:""})
                }
            },
            reduceProp(property){
                for (let i = 0; i < this.properties.length; i++) {
                    if (this.properties[i].key===property.key) {
                        this.properties.splice(i,1);
                        i--;
                    }
                }
            },
            propTypeChange() {
                if ('table'===this.propType) {
                    let text = "";
                    this.properties.forEach(e=>{
                        text = text + e.key + "=" + e.val + "\r\n";
                    })
                    text = text.substr(0,text.length-2);
                    this.propText = text;
                    this.propType = 'text';
                } else {
                    this.properties = [];
                    let list = this.propText.split("\r\n");
                    console.log(this.propText);
                    for (let i = 0; i < list.length; i++) {
                        let kv = list[i].split("=");
                        this.properties.push({
                            key: kv[0],
                            val: kv[1]
                        });
                    }
                    this.propType = 'table';
                }
            },
            onInput(e) {
                this.propText = e;
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