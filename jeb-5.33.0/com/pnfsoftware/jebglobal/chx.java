package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.wincommon.Guid;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ChannelUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.IOException;
import java.nio.channels.SeekableByteChannel;

@Ser
public class chx {
   @SerId(1)
   private Guid pC;
   @SerId(2)
   private int A;
   @SerId(3)
   private String kS;
   @SerId(4)
   private String wS;

   public static chx pC(SeekableByteChannel var0) throws IOException {
      return new chx(
         new Guid(ChannelUtil.getBytes(var0, 16)), ChannelUtil.getLEInt(var0), Strings.decodeUTF8(ChannelUtil.getBytesUntil(var0, 0, 1048576, true))
      );
   }

   public chx(Guid var1, int var2, String var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      int var4 = var3.lastIndexOf(92);
      var4 = Math.max(var4, var3.lastIndexOf(47));
      if (var4 >= 0) {
         this.wS = var3.substring(var4 + 1);
      }
   }

   public Guid pC() {
      return this.pC;
   }

   public int A() {
      return this.A;
   }

   public String kS() {
      return this.kS;
   }

   public boolean wS() {
      return this.wS != null;
   }

   public String UT() {
      return this.wS != null ? this.wS : this.kS;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.A;
      var1 = 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
      return 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         chx var2 = (chx)var1;
         if (this.A != var2.A) {
            return false;
         } else {
            if (this.pC == null) {
               if (var2.pC != null) {
                  return false;
               }
            } else if (!this.pC.equals(var2.pC)) {
               return false;
            }

            if (this.kS == null) {
               if (var2.kS != null) {
                  return false;
               }
            } else if (!this.kS.equals(var2.kS)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%s:%d", this.kS(), this.pC(), this.A());
   }

   public String E() {
      return Strings.ff("%s/%s%d/%s", this.UT(), this.pC().toString(true, false, false), this.A(), this.UT());
   }
}
