package com.pnfsoftware.jeb.corei.testbed;

import com.pnfsoftware.jeb.core.Artifact;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.properties.impl.PropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.corei.parsers.arm.rQ;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jebglobal.C;
import com.pnfsoftware.jebglobal.XR;
import com.pnfsoftware.jebglobal.awp;
import com.pnfsoftware.jebglobal.axv;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Av implements K {
   private String pC = "ARM_ARM";

   @Override
   public String pC() {
      return this.pC;
   }

   @Override
   public int A() {
      return 10;
   }

   @Override
   public Map pC(IUnit var1) {
      return null;
   }

   @Override
   public Map pC(String var1) throws IOException {
      byte[] var2 = IO.readFile(new File(var1));
      HashMap var3 = new HashMap();
      int var4 = 0;

      while (var4 < var2.length) {
         byte var5 = var2[var4];
         var4++;
         byte[] var6 = new byte[var5];
         ArrayUtil.copyBytes(var6, 0, var2, var4, var5);
         String var7 = Formatter.byteArrayToHexString(var6);

         try {
            com.pnfsoftware.jeb.corei.parsers.arm.cq var8 = new com.pnfsoftware.jeb.corei.parsers.arm.cq(32, null);
            var8.setEndianness(Endianness.LITTLE_ENDIAN);
            rQ var9 = this.pC(var7, var8);
            var3.put(var7, Strings.ff("%s", this.pC(var9, var8)));
         } catch (Exception var10) {
            var3.put(var7, "UNTRANSLATED");
         }

         var4 += var5;
      }

      return var3;
   }

   protected rQ pC(String var1, com.pnfsoftware.jeb.corei.parsers.arm.cq var2) {
      try {
         byte[] var3 = Formatter.hexStringToByteArray(var1.replaceAll("\\s+", ""));
         return (rQ)var2.parseAt(var3, 0, var3.length);
      } catch (ProcessorException var4) {
         throw new RuntimeException(var4);
      }
   }

   private String pC(rQ var1, com.pnfsoftware.jeb.corei.parsers.arm.cq var2) {
      com.pnfsoftware.jeb.corei.parsers.arm.Sv var3 = new com.pnfsoftware.jeb.corei.parsers.arm.Sv();
      var3.setMnemonicRightPaddingLength(1);
      axv var4 = new axv();
      C var5 = this.pC(var1.getCode(), var2);
      var3.setCodeUnit(var5);
      var3.formatInstruction(0L, var1, var4);
      return var4.getCurrentLine().getText().toString();
   }

   private C pC(byte[] var1, com.pnfsoftware.jeb.corei.parsers.arm.cq var2) {
      PropertyDefinitionManager var3 = new PropertyDefinitionManager();
      C var4 = new C("proc", "bin", new BytesInput(var1), new XR(false, var3, null), new Artifact("emptyArtifact", new BytesInput(new byte[0])), var3);
      var4.setVirtualImageBase(0L);
      var4.setProcessor(var2);
      var4.setMemory(new awp(32));
      var4.process();
      return var4;
   }
}
