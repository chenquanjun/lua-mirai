function test_json()
    local s=[[
    {"code":200,"curTime":1630639381812,"data":{"total":"3938","list":[{"musicrid":"MUSIC_93157","barrage":"0","artist":"林俊杰","mvpayinfo":{"play":"0","vid":"5780843","download":"0"},"pic":"https://img4.kuwo.cn/star/albumcover/120/8/46/1979391351.jpg","isstar":0,"rid":93157,"duration":269,"score100":"84","content_type":"0","track":0,"hasLossless":false,"hasmv":1,"album":"第二天堂","albumid":"15772","pay":"16711935","artistid":1062,"albumpic":"https://img4.kuwo.cn/star/albumcover/120/8/46/1979391351.jpg","originalsongtype":1,"songTimeMinutes":"04:29","isListenFee":true,"pic120":"https://img4.kuwo.cn/star/albumcover/120/8/46/1979391351.jpg","name":"江南","online":1,"payInfo":{"play":"1111","download":"1111","local_encrypt":"1","limitfree":"0","cannotDownload":"0","refrain_start":"83457","cannotOnlinePlay":"0","feeType":{"song":"1","album":"0","vip":"1","bookvip":"0"},"listen_fragment":"1","refrain_end":"119370","tips_intercept":"0"}},{"musicrid":"MUSIC_6182865","barrage":"0","artist":"林俊杰","mvpayinfo":{"play":"0","vid":"343672","download":"0"},"pic":"https://img4.kuwo.cn/star/albumcover/120/0/22/2162936724.jpg","isstar":0,"rid":6182865,"duration":298,"score100":"81","content_type":"0","track":0,"hasLossless":false,"hasmv":1,"album":"新地球","albumid":"439512","pay":"16711935","artistid":1062,"albumpic":"https://img4.kuwo.cn/star/albumcover/120/0/22/2162936724.jpg","originalsongtype":1,"songTimeMinutes":"04:58","isListenFee":true,"pic120":"https://img4.kuwo.cn/star/albumcover/120/0/22/2162936724.jpg","name":"可惜没如果-《杜鹃之巢》韩剧中文主题曲|《对我而言，可爱的她》韩剧中文片尾曲","online":1,"payInfo":{"play":"1111","download":"1111","local_encrypt":"1","limitfree":"0","cannotDownload":"0","refrain_start":"152100","cannotOnlinePlay":"0","feeType":{"song":"1","album":"0","vip":"1","bookvip":"0"},"listen_fragment":"1","refrain_end":"187719","tips_intercept":"0"}},{"musicrid":"MUSIC_96901894","barrage":"0","artist":"林俊杰","mvpayinfo":{"play":"0","vid":"0","download":"0"},"pic":"https://img4.kuwo.cn/star/albumcover/120/25/65/3249364153.jpg","isstar":0,"rid":96901894,"duration":242,"score100":"78","content_type":"0","track":0,"hasLossless":false,"hasmv":0,"album":"2003年-2010年作品精选集","albumid":"10044735","pay":"0","artistid":1062,"albumpic":"https://img4.kuwo.cn/star/albumcover/120/25/65/3249364153.jpg","originalsongtype":0,"songTimeMinutes":"04:02","isListenFee":false,"pic120":"https://img4.kuwo.cn/star/albumcover/120/25/65/3249364153.jpg","name":"曹操","online":1,"payInfo":{"play":"0000","download":"0000","local_encrypt":"0","limitfree":"0","cannotDownload":"0","refrain_start":"0","cannotOnlinePlay":"0","feeType":{"song":"0","album":"0","vip":"0","bookvip":"0"},"listen_fragment":"0","refrain_end":"0","tips_intercept":"0"}},{"musicrid":"MUSIC_438658","barrage":"0","artist":"林俊杰","mvpayinfo":{"play":"0","vid":"4023312","download":"0"},"pic":"https://img4.kuwo.cn/star/albumcover/120/12/41/1733372010.jpg","isstar":0,"rid":438658,"duration":281,"score100":"80","content_type":"0","track":0,"hasLossless":false,"hasmv":1,"album":"JJ陆","albumid":"29534","pay":"16711935","artistid":1062,"albumpic":"https://img4.kuwo.cn/star/albumcover/120/12/41/1733372010.jpg","originalsongtype":1,"songTimeMinutes":"04:41","isListenFee":true,"pic120":"https://img4.kuwo.cn/star/albumcover/120/12/41/1733372010.jpg","name":"醉赤壁-《赤壁Online》网游主题曲","online":1,"payInfo":{"play":"1111","download":"1111","local_encrypt":"1","limitfree":"0","cannotDownload":"0","refrain_start":"55001","cannotOnlinePlay":"0","feeType":{"song":"1","album":"0","vip":"1","bookvip":"0"},"listen_fragment":"1","refrain_end":"106765","tips_intercept":"0"}},{"musicrid":"MUSIC_1120967","barrage":"0","artist":"林俊杰","mvpayinfo":{"play":"0","vid":"257746","download":"0"},"pic":"https://img4.kuwo.cn/star/albumcover/120/61/84/3809260188.jpg","isstar":0,"rid":1120967,"duration":245,"score100":"79","content_type":"0","track":0,"hasLossless":false,"hasmv":1,"album":"学不会","albumid":"84137","pay":"16711935","artistid":1062,"albumpic":"https://img4.kuwo.cn/star/albumcover/120/61/84/3809260188.jpg","originalsongtype":1,"songTimeMinutes":"04:05","isListenFee":true,"pic120":"https://img4.kuwo.cn/star/albumcover/120/61/84/3809260188.jpg","name":"那些你很冒险的梦","online":1,"payInfo":{"play":"1111","download":"1111","local_encrypt":"1","limitfree":"0","cannotDownload":"0","refrain_start":"68507","cannotOnlinePlay":"0","feeType":{"song":"1","album":"0","vip":"1","bookvip":"0"},"listen_fragment":"1","refrain_end":"98170","tips_intercept":"0"}}]},"msg":"success","profileId":"site","reqId":"b52d592d46d613f225689c7ad12aaf7d","tId":""}


    ]]
    local j=Json.parseJson(s)
    print(j)
    for k,v in pairs(j) do
        print(k,v)
        if type(v)=="table" then
            for k1,v1 in pairs(v) do
                print(k1,v1)
            end
        end
    end
end
test_json()