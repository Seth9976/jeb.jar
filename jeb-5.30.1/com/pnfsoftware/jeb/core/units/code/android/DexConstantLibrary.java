package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

@Ser
public class DexConstantLibrary {
   @SerId(1)
   IDexUnit dex;
   @SerId(2)
   Map objmap = new HashMap();

   public DexConstantLibrary(IDexUnit var1) {
      this.dex = var1;
   }

   public boolean setValue(Object var1, int var2) {
      if (var1 == null || var2 < 0) {
         return false;
      } else if (!(var1 instanceof Byte)
         && !(var1 instanceof Character)
         && !(var1 instanceof Short)
         && !(var1 instanceof Integer)
         && !(var1 instanceof Long)
         && !(var1 instanceof Float)
         && !(var1 instanceof Double)) {
         return false;
      } else {
         Object var3 = (Collection)this.objmap.get(var1);
         if (var3 == null) {
            var3 = new LinkedHashSet();
            this.objmap.put(var1, var3);
         }

         var3.add(var2);
         return true;
      }
   }

   public Collection getFieldIndices(Object var1) {
      Object var2 = (Collection)this.objmap.get(var1);
      if (var2 == null) {
         var2 = Collections.emptyList();
      }

      return Collections.unmodifiableCollection((Collection)var2);
   }

   void f(byte var1, char var2) {
   }

   public Collection getFieldIndices(Object var1, boolean var2, boolean var3) {
      Object var4 = this.getFieldIndices(var1);
      if (var2 || var3) {
         var4 = new ArrayList((Collection)var4);
         if (var1 instanceof Byte var5) {
            if (var3) {
               var4.addAll(this.getFieldIndices(var5.shortValue()));
               var4.addAll(this.getFieldIndices(var5.intValue()));
               var4.addAll(this.getFieldIndices(var5.longValue()));
            }

            if (var3 && var2 && var5 <= '\uffff' && var5 >= 0) {
               char var6 = (char)var5.intValue();
               var4.addAll(this.getFieldIndices(var6));
            }
         } else if (var1 instanceof Character var7) {
            if (var3) {
               var4.addAll(this.getFieldIndices(Integer.valueOf(var7)));
               var4.addAll(this.getFieldIndices((long)var7.charValue()));
            }

            if (var3 && var2) {
               if (var7 <= 127 && var7 >= -128) {
                  var4.addAll(this.getFieldIndices((byte)var7.charValue()));
               }

               if (var7 <= 32767 && var7 >= -32768) {
                  var4.addAll(this.getFieldIndices((short)var7.charValue()));
               }
            }
         } else if (var1 instanceof Short var8) {
            if (var3) {
               var4.addAll(this.getFieldIndices(var8.intValue()));
               var4.addAll(this.getFieldIndices(var8.longValue()));
            }

            if (var2 && var8 <= 127 && var8 >= -128) {
               var4.addAll(this.getFieldIndices(var8.byteValue()));
            }

            if (var3 && var2 && var8 <= '\uffff' && var8 >= 0) {
               var4.addAll(this.getFieldIndices((char)var8.intValue()));
            }
         } else if (var1 instanceof Integer var9) {
            if (var3) {
               var4.addAll(this.getFieldIndices(var9.longValue()));
            }

            if (var2) {
               if (var9 <= 32767 && var9 >= -32768) {
                  var4.addAll(this.getFieldIndices(var9.shortValue()));
               }

               if (var9 <= 65535 && var9 >= 0) {
                  var4.addAll(this.getFieldIndices((char)var9.intValue()));
               }

               if (var9 <= 127 && var9 >= -128) {
                  var4.addAll(this.getFieldIndices(var9.byteValue()));
               }
            }
         } else if (var1 instanceof Long var10) {
            if (var2) {
               if (var10 <= 2147483647L && var10 >= -2147483648L) {
                  var4.addAll(this.getFieldIndices(var10.intValue()));
               }

               if (var10 <= 32767L && var10 >= -32768L) {
                  var4.addAll(this.getFieldIndices(var10.shortValue()));
               }

               if (var10 <= 65535L && var10 >= 0L) {
                  var4.addAll(this.getFieldIndices((char)var10.intValue()));
               }

               if (var10 <= 127L && var10 >= -128L) {
                  var4.addAll(this.getFieldIndices(var10.byteValue()));
               }
            }
         } else if (var1 instanceof Float var11) {
            if (var3) {
               var4.addAll(this.getFieldIndices(var11.doubleValue()));
            }
         } else if (var1 instanceof Double var12 && var2 && var12 <= Float.MAX_VALUE && var12 >= Float.MIN_VALUE) {
            var4.addAll(this.getFieldIndices(var12.floatValue()));
         }
      }

      return (Collection)var4;
   }
}
