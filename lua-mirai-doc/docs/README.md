---
home: true
title: Lua Mirai
heroImage: /images/logo.png
actions:
  - text: 快速开始
    link: /guide/introduce.md
    type: primary
  - text: Github
    link: https://github.com/only52607/lua-mirai
    type: secondary
features:
  - title: 简单、轻便
    details: 几行代码即可实现bot
  - title: 零配置
    details: 无需繁琐配置
  - title: 快速上手
    details: 10分钟即可上手
 
footer:  Copyright © 2021-present Lua Mirai
---


#### 实现"AI"仅需6行
```lua
Event.subscribe("FriendMessageEvent", function(event)
    local msg = event.message
    local sender = event.sender
    local rep = tostring(msg):gsub("吗",""):gsub("?","!"):gsub("？","！")
    sender:sendMessage(Quote(msg) .. rep)
end)
```