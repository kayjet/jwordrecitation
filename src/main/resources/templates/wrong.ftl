<#include "header.ftl"/>
</head>
<body>
<div id="app">
<#--  <el-button @click="visible = true">Button</el-button>
  <el-dialog :visible.sync="visible" title="Hello world">
      <p>Try Element</p>
  </el-dialog>-->
    <el-row>
        <div class="header">
            <el-button type="default" size="mini" @click="back">返回首页</el-button>
            <el-button type="primary" size="mini" @click="review">随机复习</el-button>
        </div>
    </el-row>

    <el-row v-for="wg in wordGroupList">
        <el-col>
            <div class="grid-content bg-purple" @click="toWord(wg)">
                <p>创建日：{{wg.createTime | formatDate}}</p>
                <p>假名：{{wg.katakana}}</p>
                <p v-if="wg.katakanaCn">汉字：{{wg.katakanaCn}}</p>
                <p>解释：{{wg.explaination}}</p>
                <p  v-if="wg.wordType">词性：{{wg.wordType}}</p>
                <p>错误次数：{{wg.wrongTimes}}</p>
            </div>
        </el-col>
    </el-row>

</div>

<script>
    new Vue({
        el: '#app',
        data: function () {
            return {
                wordList: {},
                visible: false,
                wordGroupList: [],
            }
        },
        created: function () {
            this.getWordGroupList();
        },
        methods: {
            back: function () {
                window.location.href = '/index';
            },
            review: function () {
                window.location.href = '/reviewWrongWordsView';
            },
            getWordGroupList: function () {
                var that = this;
                axios.get('/getWrongWordsList').then(function (response) {
                    that.wordGroupList = response.data;
                    console.log(response.data);
                }).catch(function (error) {
                    console.log(error);
                });
            }
        }
    })
</script>
</body>
</html>