package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.util.encoding.cbor.CborDecoder;
import com.pnfsoftware.jeb.util.encoding.cbor.CborType;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Rd {
   private static final ILogger q = GlobalLog.getLogger(Rd.class);
   private byte[] RF;
   private Integer xK;
   private Map Dw;

   public static boolean q(byte[] var0) {
      if (var0.length < 2) {
         return false;
      } else {
         short var1 = EndianUtil.bigEndianBytesToShort(var0, var0.length - 2);
         if (var1 > 0 && var1 < var0.length) {
            int var2 = var0.length - 2 - var1;
            return var2 >= 0;
         } else {
            return false;
         }
      }
   }

   public Rd(byte[] var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.RF = var1;
      }
   }

   public void q() throws IOException {
      if (this.RF.length < 2) {
         throw new IOException("Not enough data");
      } else {
         short var1 = EndianUtil.bigEndianBytesToShort(this.RF, this.RF.length - 2);
         if (var1 > 0 && var1 < this.RF.length) {
            int var2 = this.RF.length - 2 - var1;
            if (var2 < 0) {
               throw new IOException("Not enough data");
            } else {
               this.xK = var2;
               CborDecoder var3 = new CborDecoder(new ByteArrayInputStream(this.RF, var2, var1));
               CborType var4 = var3.peekType();
               if (var4.getMajorType() != 5) {
                  throw new RuntimeException("Unexpected metadata container: " + var4);
               } else {
                  int var5 = (int)var3.readMapLength();
                  this.Dw = new LinkedHashMap(var5);

                  for (int var6 = 0; var6 < var5; var6++) {
                     var4 = var3.peekType();
                     if (var4.getMajorType() != 3) {
                        throw new RuntimeException("Unexpected key type: " + var4);
                     }

                     String var7 = var3.readTextString();
                     var4 = var3.peekType();
                     Object var8;
                     if (var4.getMajorType() == 3) {
                        var8 = var3.readTextString();
                     } else {
                        if (var4.getMajorType() != 2) {
                           throw new RuntimeException("Need support for value type: " + var4);
                        }

                        var8 = var3.readByteString();
                     }

                     this.Dw.put(var7, var8);
                  }
               }
            }
         } else {
            throw new IOException("No metadata");
         }
      }
   }

   public Map RF() {
      return this.Dw;
   }

   public Integer xK() {
      return this.xK;
   }

   public Integer Dw() {
      return this.xK == null ? null : this.RF.length;
   }
}
