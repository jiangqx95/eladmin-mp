/*
 *  Copyright 2019-2023 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package me.zhengjie.config.mybatis;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import me.zhengjie.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author Zheng Jie
 * @description
 * @date 2023-06-13
 **/
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        /* 创建时间 */
        this.setFieldValByName("createTime", DateTime.now().toTimestamp(), metaObject);
        this.setFieldValByName("updateTime", DateTime.now().toTimestamp(), metaObject);
        /* 操作人 */
        String username;
        try {
            username = SecurityUtils.getCurrentUsername();
        } catch (Exception ignored) {
            // 写日志是异步操作,已经带上了用户名,这里获取不到用户信息,直接用
            username = metaObject.getValue("username").toString();
        }
        this.setFieldValByName("createBy", username, metaObject);
        this.setFieldValByName("updateBy", username, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        /* 更新时间 */
        this.setFieldValByName("updateTime", DateTime.now().toTimestamp(), metaObject);
        /* 操作人 */
        String username = SecurityUtils.getCurrentUsername();
        this.setFieldValByName("updateBy", username, metaObject);
    }
}

