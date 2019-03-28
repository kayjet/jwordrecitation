<#include "header.ftl"/>
</head>
<body>
<div id="app">
    <el-row>
        <div class="header">
            <el-button type="default" size="mini" @click="back">返回首页</el-button>
            <el-button type="primary" size="mini" @click="review">复习</el-button>
            <el-button type="success" size="mini" v-if="isTesting" @click="submit">提交</el-button>
        </div>
    </el-row>

<#list wrongWords as w>
    <el-row>
        <el-col>
            <div class="grid-content bg-purple" id="word${w_index}" data-wordid="${w.tid}">
                <div>
                    <p v-if="!isTesting">假名：${w.katakana}</p>
                    <p v-if="isTesting">假名：<input type="text" v-model="testKatakana${w_index}"></p>
                </div>

                <#if w.katakanaCn??>
                    <div>
                        <p v-if="!isTesting">汉字：${w.katakanaCn}</p>
                        <p v-if="isTesting">汉字：<input type="text" v-model="testKatakanaCn${w_index}"></p>
                    </div>
                </#if>
                <p>解释：${w.explaination}</p>
                <#if w.wordType??>
                    <p>词性：${w.wordType}</p>
                </#if>
            </div>
        </el-col>
    </el-row>
</#list>
</div>

<script>

    new Vue({
        el: '#app',
        data: function () {
            return {
                words:${wrongWordsJson},
                isTesting: false
            }
        },
        created: function () {
        },
        mounted: function () {
            console.log("mounted");
        },
        methods: {
            back: function () {
                window.location.href = '/wrongView';
            },
            submit: function () {
//
                var words = this.words;
                var wrongWordIds = [];
                for (var i = 0; i < words.length; i++) {
//                    var wid = document.getElementById("word" + i).getAttribute("data-wordid");
                    var testKatakana = this['testKatakana' + i];
                    var testKatakanaCn = this['testKatakanaCn' + i];
                    var word = {
                        katakana: testKatakana,
                        katakanaCn: undefined
                    };
                    if (testKatakanaCn != undefined) {
                        word.katakanaCn = testKatakanaCn;
                    }
                    if (words[i].katakana == word.katakana && words[i].katakanaCn == word.katakanaCn) {
//                        alert("正确")
                        document.getElementById("word" + i).style.color = 'green';
                    } else {
//                        alert("错误")
                        document.getElementById("word" + i).style.color = 'red';
                        wrongWordIds.push(words[i].tid);
                    }
                }
                this.isTesting = false;
            },
            review: function () {
                this.isTesting = true;
            }
        }
    })
</script>
</body>
</html>