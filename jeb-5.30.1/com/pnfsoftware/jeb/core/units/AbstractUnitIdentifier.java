package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.AbstractPlugin;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyDefinitionManager;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.ITypeIdProvider;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public abstract class AbstractUnitIdentifier extends AbstractPlugin implements IUnitIdentifier {
   protected String type;
   protected double priority;
   protected IPropertyDefinitionManager pdm;
   protected IPropertyManager pm;

   public AbstractUnitIdentifier(String var1, double var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("A unit identifier must have a valid type");
      } else {
         this.type = var1;
         this.priority = var2;
      }
   }

   @Override
   public void initialize(IPropertyDefinitionManager var1) {
      this.createPDM(var1, null, 0);
   }

   protected final void createPDM(IPropertyDefinitionManager var1, String var2, int var3) {
      if (var2 == null) {
         var2 = Strings.ff(S.L("Properties for unit plugin: %s"), this.getFormatType());
      }

      this.pdm = new PropertyDefinitionManager(this.type, var1, var2, var3);
   }

   @Override
   public IPropertyDefinitionManager getPropertyDefinitionManager() {
      return this.pdm;
   }

   @Override
   public String getFormatType() {
      return this.type;
   }

   @Override
   public double getPriority() {
      return this.priority;
   }

   @Override
   public ITypeIdProvider getTypeIdProvider() {
      return null;
   }

   public static boolean checkBytes(byte[] var0, int var1, byte... var2) {
      return checkBytes(var0, var1, true, var2);
   }

   public static boolean checkBytes(byte[] var0, int var1, boolean var2, byte... var3) {
      if (var0 != null && var1 + var3.length <= var0.length) {
         for (byte var7 : var3) {
            if (!var2 && var0[var1] >= 65 && var0[var1] <= 90) {
               if ((var0[var1] | 32) != var7) {
                  return false;
               }
            } else if (var0[var1] != var7) {
               return false;
            }

            var1++;
         }

         return true;
      } else {
         return false;
      }
   }

   public static boolean checkBytes(byte[] var0, int var1, int... var2) {
      return checkBytes(var0, var1, true, ArrayUtil.integersToBytes(var2));
   }

   public static boolean checkBytes(byte[] var0, int var1, boolean var2, int... var3) {
      return checkBytes(var0, var1, var2, ArrayUtil.integersToBytes(var3));
   }

   public static boolean checkBytes(IInput var0, int var1, byte... var2) {
      if (var0 != null && var0.getHeader() != null) {
         if (checkBytes(var0.getHeader(), var1, var2)) {
            return true;
         } else if (var1 + var2.length <= var0.getHeader().limit()) {
            return false;
         } else {
            try {
               boolean var4;
               try (InputStream var3 = var0.getStream()) {
                  var4 = var3.skip(var1) == var1 && checkBytes(var3, var2);
               }

               return var4;
            } catch (IOException var8) {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   public static boolean checkBytes(IInput var0, int var1, int... var2) {
      return checkBytes(var0, var1, ArrayUtil.integersToBytes(var2));
   }

   public static boolean checkBytes(IInput var0, int var1, String var2) {
      return checkBytes(var0, var1, Strings.encodeUTF8(var2));
   }

   public static int readHeaderByte(IInput var0, int var1) {
      if (var0 != null && var0.getHeader() != null) {
         ByteBuffer var2 = var0.getHeader();
         return var2 != null && var1 < var2.limit() ? var2.get(var1) : -1;
      } else {
         return -1;
      }
   }

   private static boolean checkBytes(ByteBuffer var0, int var1, byte... var2) {
      if (var0 != null && var1 + var2.length <= var0.limit()) {
         for (byte var6 : var2) {
            if (var0.get(var1) != var6) {
               return false;
            }

            var1++;
         }

         return true;
      } else {
         return false;
      }
   }

   private static boolean checkBytes(InputStream var0, byte... var1) throws IOException {
      if (var0 != null && var0.available() >= var1.length) {
         for (byte var5 : var1) {
            int var6 = var0.read();
            if (var6 == -1 || var6 != var5) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static byte[] getNonWhitespaceHeader(IInput var0, int var1, char... var2) {
      return getNonWhitespaceHeader(var0, var1, false, var2);
   }

   public static byte[] getNonWhitespaceHeader(IInput var0, int var1, boolean var2, char... var3) {
      ByteBuffer var4 = var0.getHeader();
      byte[] var5 = new byte[var4.limit()];
      var4.get(var5);
      if (!var2 && var5.length == var0.getCurrentSize()) {
         return var5;
      } else {
         int var6 = getTextFirstIndex(var5, 0, var3);
         if (!var2 && var6 + var1 < var5.length) {
            return var5;
         } else {
            try {
               byte[] var17;
               try (InputStream var7 = var0.getStream()) {
                  var7.skip(var6);

                  int var8;
                  for (var8 = var7.read(); Strings.isAsciiWhitespace(var8, var3); var8 = var7.read()) {
                     if (var7.available() <= 0) {
                        return null;
                     }
                  }

                  if (var1 == 1) {
                     return new byte[]{(byte)var8};
                  }

                  ByteArrayOutputStream var15 = new ByteArrayOutputStream();
                  var15.write(var8);

                  for (int var10 = 1; var10 < var1; var10++) {
                     if (var7.available() <= 0) {
                        return var15.toByteArray();
                     }

                     byte var11 = (byte)var7.read();
                     var15.write(var11);
                     if (Strings.isAsciiWhitespace(var11, var3)) {
                        var10--;
                     }
                  }

                  var17 = var15.toByteArray();
               }

               return var17;
            } catch (IOException var14) {
               return null;
            }
         }
      }
   }

   protected static int getTextFirstIndex(byte[] var0, int var1, char... var2) {
      if (var1 == 0 && var0.length > 4) {
         var1 = Strings.getBOMSize(var0);
      }

      while (var1 < var0.length) {
         if (!Strings.isAsciiWhitespace(var0[var1], var2)) {
            return var1;
         }

         var1++;
      }

      return var1;
   }

   @Override
   public boolean acceptAnyInputBytes() {
      return false;
   }
}
