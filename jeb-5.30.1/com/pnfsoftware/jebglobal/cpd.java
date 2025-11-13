package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.wincommon.Guid;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ChannelUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.IOException;
import java.nio.channels.SeekableByteChannel;

@Ser
public class cpd {
   @SerId(1)
   private Guid q;
   @SerId(2)
   private int RF;
   @SerId(3)
   private String xK;
   @SerId(4)
   private String Dw;

   public static cpd q(SeekableByteChannel var0) throws IOException {
      return new cpd(
         new Guid(ChannelUtil.getBytes(var0, 16)), ChannelUtil.getLEInt(var0), Strings.decodeUTF8(ChannelUtil.getBytesUntil(var0, 0, 1048576, true))
      );
   }

   public cpd(Guid var1, int var2, String var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      int var4 = var3.lastIndexOf(92);
      var4 = Math.max(var4, var3.lastIndexOf(47));
      if (var4 >= 0) {
         this.Dw = var3.substring(var4 + 1);
      }
   }

   public Guid q() {
      return this.q;
   }

   public int RF() {
      return this.RF;
   }

   public String xK() {
      return this.xK;
   }

   public boolean Dw() {
      return this.Dw != null;
   }

   public String Uv() {
      return this.Dw != null ? this.Dw : this.xK;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.RF;
      var1 = 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
      return 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
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
         cpd var2 = (cpd)var1;
         if (this.RF != var2.RF) {
            return false;
         } else {
            if (this.q == null) {
               if (var2.q != null) {
                  return false;
               }
            } else if (!this.q.equals(var2.q)) {
               return false;
            }

            if (this.xK == null) {
               if (var2.xK != null) {
                  return false;
               }
            } else if (!this.xK.equals(var2.xK)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%s:%d", this.xK(), this.q(), this.RF());
   }

   public String oW() {
      return Strings.ff("%s/%s%d/%s", this.Uv(), this.q().toString(true, false, false), this.RF(), this.Uv());
   }
}
