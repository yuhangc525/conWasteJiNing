webpackJsonp([27],{"3rgu":function(t,a,n){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var e=n("ptRb"),i={data:function(){return{isFirstClick:0,currentPoint:new Object,isShow:!1,tableData:{},data_info:[]}},mounted:function(){this.ready()},watch:{currentPoint:{handler:function(t,a){if(this.isFirstClick>1){var n=new BMap.Icon("../../../static/img/point1.png",new BMap.Size(32,32),{anchor:new BMap.Size(16,32)});a.setIcon(n)}}}},methods:{ready:function(){var t=this,a=new BMap.Map("allmap");a.centerAndZoom(new BMap.Point(114.035761,22.56381),10),a.addControl(new BMap.MapTypeControl({mapTypes:[BMAP_NORMAL_MAP,BMAP_SATELLITE_MAP,BMAP_HYBRID_MAP]}));var n=new BMap.ScaleControl({anchor:BMAP_ANCHOR_TOP_LEFT});a.addControl(n);var i=new BMap.NavigationControl;a.addControl(i);var r=new BMap.Size(10,20);a.addControl(new BMap.CityListControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,offset:r}));var o=new BMap.Icon("../../../static/img/point1.png",new BMap.Size(32,32),{anchor:new BMap.Size(16,32)}),s=new BMap.Icon("../../../static/img/point2.png",new BMap.Size(32,32),{anchor:new BMap.Size(16,32)});Object(e.z)(!1).then(function(n){t.data_info=n.data.respBody;for(var e=[],i=0;i<t.data_info.length;i++){var r=new BMap.Marker(new BMap.Point(t.data_info[i].resourceLong,t.data_info[i].resourceLat),{icon:o});e.push(r),a.addOverlay(r),r.setAnimation(BMAP_ANIMATION_BOUNCE)}var c=t;for(i=0;i<e.length;i++)!function(){var t=i;e[i].addEventListener("click",function(a){c.currentPoint=e[t],c.tableData=c.data_info[t],c.isShow=!0,this.setIcon(s),c.isFirstClick+=1})}()}).catch(function(t){return console.log(t)}),a.setCurrentCity("北京"),a.enableScrollWheelZoom(!0),a.enableInertialDragging(),a.enableContinuousZoom()},cancel:function(){this.isShow=!1;var t=new BMap.Icon("../../../static/img/point1.png",new BMap.Size(32,32),{anchor:new BMap.Size(16,32)});this.currentPoint.setIcon(t)}}},r={render:function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{attrs:{id:"resourceMonitor"}},[e("transition",[e("div",{directives:[{name:"show",rawName:"v-show",value:t.isShow,expression:"isShow"}],attrs:{id:"rightContent"}},[e("div",{staticClass:"title"},[e("span",[t._v("资源场基本信息")]),t._v(" "),e("img",{attrs:{src:n("PY/F"),alt:"箭头"}}),t._v(" "),e("img",{attrs:{id:"img2",src:n("iIUG"),alt:"取消",title:"退出"},on:{click:t.cancel}})]),t._v(" "),e("table",{staticClass:"table"},[e("tr",[e("th",[t._v("编号")]),t._v(" "),e("td",[t._v(t._s(t.tableData.resourcePlantId))])]),t._v(" "),e("tr",[e("th",[t._v("工地名称")]),t._v(" "),e("td",[t._v(t._s(t.tableData.resourcePlantName))])]),t._v(" "),e("tr",[e("th",[t._v("地址")]),t._v(" "),e("td",[t._v(t._s(t.tableData.resourcePlantAddress))])]),t._v(" "),e("tr",[e("th",[t._v("上报时间")]),t._v(" "),e("td",[t._v(t._s(t.tableData.inputTime))])]),t._v(" "),e("tr",[e("th",[t._v("属性")]),t._v(" "),e("td",[t._v(t._s(t.tableData.resourcePlantType))])]),t._v(" "),e("tr",[e("th",[t._v("垃圾类型")]),t._v(" "),e("td",[t._v(t._s(t.tableData.dailyAcceptance))])]),t._v(" "),e("tr",[e("th",[t._v("日处理量")]),t._v(" "),e("td",[t._v(t._s(t.tableData.dailyOutput))])]),t._v(" "),e("tr",[e("th",[t._v("责任人")]),t._v(" "),e("td",[t._v(t._s(t.tableData.responsiblePerson))])]),t._v(" "),e("tr",[e("th",[t._v("备注")]),t._v(" "),e("td",[t._v(t._s(t.tableData.content))])])])])]),t._v(" "),e("div",{attrs:{id:"allmap"}})],1)},staticRenderFns:[]};var o=n("C7Lr")(i,r,!1,function(t){n("WwoV")},"data-v-8f714286",null);a.default=o.exports},WwoV:function(t,a){}});
//# sourceMappingURL=27.32fae38cd44c56a51688.js.map