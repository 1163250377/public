# 说明
  #### jar下载 https://github.com/1163250377/public/releases/download/v1.0/address-analysis.jar
  #### address_analysis文件夹可直接用eclipse打开
## 项目说明
  #### 1.根据一个地址获取这个地址的5级行政编码
  #### 2.根据行政编码获取标准地址
  #### 3.根据地址获取json格式的下属行政区
# 项目数据来源
  #### https://github.com/modood/Administrative-divisions-of-China
  
# 示例
  ### AreacodeFinder area = AreacodeFinder.newInstance();
  ### area.searchByAddress("湖北通山县");
  ### 返回: 421224000000
  
  ### area.searchByCode("421224000000");
  ### 返回: 湖北省咸宁市通山县
  ### area.getJSON("421224000000"); 或者 area.getJSON("咸宁市通山县");
  ### 返回: {"code":"421224","name":"通山县","children":[{"code":"421224100","name":"通羊镇"},{"code":"421224101","name":"南林桥镇"},{"code":"421224102","name":"黄沙铺镇"},{"code":"421224103","name":"厦铺镇"},{"code":"421224104","name":"九宫山镇"},{"code":"421224105","name":"闯王镇"},{"code":"421224106","name":"洪港镇"},{"code":"421224107","name":"大畈镇"},{"code":"421224200","name":"大路乡"},{"code":"421224201","name":"杨芳林乡"},{"code":"421224202","name":"燕厦乡"},{"code":"421224203","name":"慈口乡"},{"code":"421224450","name":"九宫山自然保护管理局"},{"code":"421224570","name":"开发区"}]}
  ### 可返回任意级的 行政区划列表 area.getJSON("中国"); 返回省列表
