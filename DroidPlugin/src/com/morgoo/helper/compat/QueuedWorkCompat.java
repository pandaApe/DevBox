/*
**        DroidPlugin Project
**
** Copyright(c) 2015 Andy Zhang <zhangyong232@gmail.com>
**
** This file is part of DroidPlugin.
**
** DroidPlugin is free software: you can redistribute it and/or
** modify it under the terms of the GNU Lesser General Public
** License as published by the Free Software Foundation, either
** version 3 of the License, or (at your option) any later version.
**
** DroidPlugin is distributed in the hope that it will be useful,
** but WITHOUT ANY WARRANTY; without even the implied warranty of
** MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
** Lesser General Public License for more details.
**
** You should have received a copy of the GNU Lesser General Public
** License along with DroidPlugin.  If not, see <http://www.gnu.org/licenses/lgpl.txt>
**
**/

package com.morgoo.helper.compat;

import com.morgoo.droidplugin.reflect.MethodUtils;

/**
 * Created by Andy Zhang(zhangyong232@gmail.com) on 2015/5/1.
 */
public class QueuedWorkCompat {

    private static Class sClass;

    private static Class Class() throws ClassNotFoundException {
        if (sClass == null) {
            sClass = Class.forName("android.app.QueuedWork");
        }
        return sClass;
    }

    public static void waitToFinish() {
        try {
            MethodUtils.invokeStaticMethod(Class(), "waitToFinish");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
