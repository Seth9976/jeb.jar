package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.units.code.simatic.IS7Block;
import com.pnfsoftware.jeb.core.units.code.simatic.S7;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TimeFormatter;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public abstract class p implements IS7Block {
   @Override
   public S7.LangType getSourceLanguage() {
      return S7.LangType.fromId(this.getSourceLanguageId());
   }

   @Override
   public S7.BlockType getType() {
      return S7.BlockType.fromId(this.getTypeId());
   }

   @Override
   public boolean isInstanceDataBlock() {
      if (this.getType() == S7.BlockType.DI) {
         return true;
      } else if (this.getType() == S7.BlockType.DB && this.getInterfaceSize() > 0) {
         int var1 = this.getBlockByte(this.getInterfaceOffset());
         return var1 == 10;
      } else {
         return false;
      }
   }

   protected String pC(byte[] var1, int var2) {
      return pC(8, var1, var2);
   }

   public static String pC(int var0, byte[] var1, int var2) {
      StringBuilder var3 = new StringBuilder();

      for (int var4 = var2; var4 < var2 + var0; var4++) {
         char var5 = (char)(var1[var4] & 255);
         if (var5 == 0) {
            break;
         }

         var3.append(var5);
      }

      return var3.toString();
   }

   protected long A(byte[] var1, int var2) {
      int var3 = EndianUtil.bigEndianBytesToInt(var1, var2);
      Assert.a(var3 >= 0 && var3 < 86400000);
      short var4 = EndianUtil.bigEndianBytesToShort(var1, var2 + 4);
      return (441763200L + var4 * 86400L) * 1000L + var3;
   }

   @Override
   public String getName() {
      return pC(this.getType(), this.getNumber());
   }

   public static String pC(S7.BlockType var0, int var1) {
      return Strings.ff("%s%d", var0 == null ? "?" : var0.toString(), var1);
   }

   @Override
   public String toString() {
      return Strings.ff(
         "%s%d, version %d.%d, programmed in %s, modified on %s, interface modified on %s",
         this.getType(),
         this.getNumber(),
         this.getVersion()[0],
         this.getVersion()[1],
         this.getSourceLanguage(),
         TimeFormatter.formatTimestampLocal(this.getModificationTimestamp()),
         TimeFormatter.formatTimestampLocal(this.getInterfaceModificationTimestamp())
      );
   }
}
