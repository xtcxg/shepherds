<template>
    <div id="app">
        <el-container style="height: 100%;padding-top: 0">
            <el-aside width="150px">
                <el-col :span="15" >
                    <el-menu default-active="0" >
                        <p style="font-family: 'Consolas',monospace; font-size: 1.5em;" v-on:click="load('/views/cont.html')">Shepherds</p>
                        <el-menu-item index="2" class="menu" v-on:click="chose('display')">
                            <i class="el-icon-menu"></i>
                            <span slot="title" style="font-size: 20px">展示</span>
                        </el-menu-item>
                        <el-menu-item index="3" class="menu" v-on:click="chose('admin')">
                            <i class="el-icon-open"></i>
                            <span slot="title" style="font-size: 20px">管理</span>
                        </el-menu-item>
                        <el-menu-item index="4" class="menu" v-on:click="chose('display')">
                            <i class="el-icon-plus"></i>
                            <span slot="title" style="font-size: 20px">创建</span>
                        </el-menu-item>
                        <el-menu-item index="5" class="menu" v-on:click="chose('plugin')">
                            <i class="el-icon-setting"></i>
                            <span slot="title" style="font-size: 20px">插件</span>
                        </el-menu-item>
                    </el-menu>
                </el-col>
            </el-aside>
            <el-main v-if="this.select==='admin'">
                <Admin/>
            </el-main>
            <el-main v-else-if="this.select==='display'">
                <Display/>
            </el-main>
            <el-main v-else-if="this.select==='plugin'">
                <Plugin/>
            </el-main>

        </el-container>
    </div>
</template>

<script>
    import Admin from "./components/Admin";
    import Display from "./components/Display";
    import Plugin from "./components/Plugin";

    export default {
        name: "app",
        components: {Admin,Display,Plugin},
        data() {
            return {
                select: "admin",
                mainPath: "/views/cont.html",
                mainPage: "",
                isCollapse: true,
            };
        },
        methods: {
            load(url) {
                this.$http.get(url).then((response) => {
                    if (response.ok) {
                        this.mainPage = response.data;
                    } else {
                        this.$notify.error({
                            duration: 1500,
                            title: response.status,
                            message: response.statusText
                        });
                    }
                }, (err)=>{
                    this.$notify.error({
                        duration: 1500,
                        title: err.status,
                        message: err.statusText
                    });
                })
            },
            chose(s){
                this.select = s;
                console.log(this.select)
            }
        },
        mounted () {
            // this.load("/views/cont.html");
        },
    };
</script>

<style>
    body {
        margin: 0;
        padding: 0;
    }
    #app {
        font-family: "Avenir", Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 0;
        height: 100%;
        width: 100%;
        position: fixed;
    }
    .el-aside {
        padding: 0;
        margin: 0;
        box-shadow: 2px 0 10px rgba(0,0,0,.2);
    }
    .el-col.el-col-15 {
        width: 100%;
    }
    .menu {
        margin-left: -30px;
    }
    .el-menu-item.is-active {
        background-color: rgba(0,128,128,0.1);
    }

</style>
