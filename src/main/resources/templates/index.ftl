<#include "header.ftl"/>
</head>
<body>
<div id="app">
    <div style="position: fixed;top: 0px;width: 100%;z-index: 999;background: #ffffff;border-bottom: 1px solid #ccc;">
        <p>复习的原则<br/>时间间隔：30秒 1分钟 5分钟 30分钟 1小时 8小时 1天 2天 6天 31天</p>
        <p>习得总数：${countInfo.all}</p>
        <p>昨日新增：${countInfo.yesterdayAdded}</p>
        <p>昨日复习：${countInfo.yesterdayReviewed}</p>
    </div>
<#--  <el-button @click="visible = true">Button</el-button>
  <el-dialog :visible.sync="visible" title="Hello world">
      <p>Try Element</p>
  </el-dialog>-->
    <el-row style="margin-top: 140px;">
        <div class="header">
            <a type="primary" size="mini" href="/createView">新增单词表</a>
            <a type="primary" size="mini" href="/wrongView" style="color: red;">错误率较高的词</a>
        </div>

    </el-row>


    <el-row v-for="wg in wordGroupList">
        <el-col>
            <div class="grid-content bg-purple"
                 v-bind:class="{ error: (wg.forgetCurve * 100 ) < 21}" @click="toWord(wg)">
                <p>创建日：{{wg.createTime | formatDate}}</p>
                <p>最近复习：{{wg.lastReviewDatetime | formatDate}}</p>
                <p >记忆率：{{wg.forgetCurve * 100 +'%'}}</p>
                <p >单词数：{{wg.words.length}}</p>
                <p>复习数：{{wg.reviewTimes}}</p>
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
            toWord: function (word) {
                console.log(word);
                window.location.href = '/reviewView?groupId=' + word.tid;
            },
            getWordGroupList: function () {
                var that = this;
                axios.get('/getGroupList').then(function (response) {
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