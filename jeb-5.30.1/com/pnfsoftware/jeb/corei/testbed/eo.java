package com.pnfsoftware.jeb.corei.testbed;

import com.pnfsoftware.jeb.core.Artifact;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.properties.impl.PropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jebglobal.FE;
import com.pnfsoftware.jebglobal.abg;
import com.pnfsoftware.jebglobal.azm;
import com.pnfsoftware.jebglobal.bat;
import com.pnfsoftware.jebglobal.fA;
import com.pnfsoftware.jebglobal.vh;
import com.pnfsoftware.jebglobal.wq;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class eo implements nI {
   private String Uv = "ARM_ARM";

   @Override
   public String q() {
      return this.Uv;
   }

   @Override
   public int RF() {
      return 10;
   }

   @Override
   public Map q(IUnit var1) {
      return null;
   }

   @Override
   public Map q(String var1) throws IOException {
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
            vh var8 = new vh(32, null);
            var8.setEndianness(Endianness.LITTLE_ENDIAN);
            fA var9 = this.q(var7, var8);
            var3.put(var7, Strings.ff("%s", this.q(var9, var8)));
         } catch (Exception var10) {
            var3.put(var7, "UNTRANSLATED");
         }

         var4 += var5;
      }

      return var3;
   }

   protected fA q(String var1, vh var2) {
      try {
         byte[] var3 = Formatter.hexStringToByteArray(var1.replaceAll("\\s+", ""));
         return (fA)var2.parseAt(var3, 0, var3.length);
      } catch (ProcessorException var4) {
         throw new RuntimeException(var4);
      }
   }

   private String q(fA var1, vh var2) {
      FE var3 = new FE();
      var3.setMnemonicRightPaddingLength(1);
      bat var4 = new bat();
      abg var5 = this.q(var1.getCode(), var2);
      var3.setCodeUnit(var5);
      var3.formatInstruction(0L, var1, var4);
      return var4.getCurrentLine().getText().toString();
   }

   private abg q(byte[] var1, vh var2) {
      PropertyDefinitionManager var3 = new PropertyDefinitionManager();
      abg var4 = new abg("proc", "bin", new BytesInput(var1), new wq(false, var3, null), new Artifact("emptyArtifact", new BytesInput(new byte[0])), var3);
      var4.setVirtualImageBase(0L);
      var4.setProcessor(var2);
      var4.setMemory(new azm(32));
      var4.process();
      return var4;
   }
}
