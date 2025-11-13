package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Map;

@Ser
public interface IUserDataSupport {
   void setData(Object var1, Object var2, boolean var3);

   Object getData(Object var1);

   void clearAllData(Object var1);

   Map getAllData();
}
