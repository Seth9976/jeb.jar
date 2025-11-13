package com.pnfsoftware.jeb.core.properties;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Set;

@Ser
public interface IConfiguration {
   void clearProperty(String var1);

   void setProperty(String var1, Object var2);

   Object getProperty(String var1);

   Set getAllPropertyKeys();
}
