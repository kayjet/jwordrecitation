<#include "header.ftl"/>
</head>
<body>
<div id="app">
    <el-row>
        <div class="header">
            <el-button type="default" size="mini" @click="back">返回首页</el-button>
            <el-button type="success" size="mini" @click="openAddingWordDialog">添加单词</el-button>
            <el-button type="primary" size="mini" @click="saveWordListToServer">保存列表</el-button>
        </div>
    </el-row>

    <el-row v-for="w in wordList">
        <el-col>
            <div class="grid-content bg-purple">
                <p>假名：{{w.katakana}}</p>
                <p v-if="w.katakanaCn">汉字：{{w.katakanaCn}}</p>
                <p>解释：{{w.explaination}}</p>
                <p>词性：{{w.wordType}}</p>
            </div>
        </el-col>
    </el-row>
    <el-dialog
            title="添加单词"
            :visible.sync="centerDialogVisible"
            width="90%"
            center>

        <el-form label-position="right" label-width="50px" :model="word">
            <el-form-item label="假名">
                <el-input v-model="word.katakana"></el-input>
            </el-form-item>
            <el-form-item label="汉字">
                <el-input v-model="word.katakanaCn"></el-input>
            </el-form-item>
            <el-form-item label="解释">
                <el-input v-model="word.explaination"></el-input>
            </el-form-item>
            <el-form-item label="词性">
                    <el-select v-model="word.wordType" placeholder="请选择">
                        <el-option
                                v-for="item in options"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="addWord">添加</el-button>
  </span>
    </el-dialog>

</div>

<script>
    new Vue({
        el: '#app',
        data: function () {
            return {
                wordList: [],
                word: {},
                options: [{
                    value: '动1',
                    label: '动1'
                }, {
                    value: '动2',
                    label: '动2'
                },  {
                    value: '动3',
                    label: '动3'
                }, {
                    value: '名',
                    label: '名'
                }, {
                    value: '形1',
                    label: '形1'
                }, {
                    value: '形2',
                    label: '形2'
                }],
                centerDialogVisible: false,
            }
        },
        methods: {
            back: function () {
                window.location.href = '/index';
            },
            openAddingWordDialog: function () {
                this.centerDialogVisible = true;
                this.word = {};
            },
            addWord: function () {
                this.centerDialogVisible = false;
                if (this.word.katakana != '' && this.word.explaination != '') {
                    this.wordList.push(this.word);

                } else {
                    alert("请填写单词");
                }
            },
            saveWordListToServer: function () {
                axios.post('/createGroup', this.wordList).then(function (response) {
                    console.log(response);
                    alert("创建成功");
                    window.location.href = "/index";
                }).catch(function (error) {
                    console.log(error);
                });
            },
        }
    })
</script>
</body>
</html>