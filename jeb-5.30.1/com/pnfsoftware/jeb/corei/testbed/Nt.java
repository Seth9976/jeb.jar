package com.pnfsoftware.jeb.corei.testbed;

import com.pnfsoftware.jeb.core.Artifact;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.properties.impl.PropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.abg;
import com.pnfsoftware.jebglobal.azm;
import com.pnfsoftware.jebglobal.bat;
import com.pnfsoftware.jebglobal.crn;
import com.pnfsoftware.jebglobal.ctc;
import com.pnfsoftware.jebglobal.cti;
import com.pnfsoftware.jebglobal.wq;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Nt implements nI {
   private static final ILogger Uv = GlobalLog.getLogger(Nt.class);
   private String oW = "X86";

   @Override
   public String q() {
      return this.oW;
   }

   @Override
   public int RF() {
      return 10;
   }

   @Override
   public Map q(String var1) throws IOException {
      cti var2 = new cti(32);
      byte[] var3 = IO.readFile(new File(var1));
      HashMap var4 = new HashMap();
      int var5 = 0;

      while (var5 < var3.length) {
         byte var6 = var3[var5];
         var5++;
         byte[] var7 = new byte[var6];
         ArrayUtil.copyBytes(var7, 0, var3, var5, var6);
         String var8 = Formatter.byteArrayToHexString(var7);

         try {
            ctc var9 = this.q(var8, var2);
            var4.put(var8, Strings.ff("%s", this.q(var9, var2)));
         } catch (Exception var10) {
            var4.put(var8, "UNTRANSLATED");
         }

         var5 += var6;
      }

      return var4;
   }

   protected ctc q(String var1, cti var2) {
      try {
         byte[] var3 = Formatter.hexStringToByteArray(var1.replaceAll("\\s+", ""));
         return (ctc)var2.parseAt(var3, 0, var3.length);
      } catch (ProcessorException var4) {
         throw new RuntimeException(var4);
      }
   }

   private String q(ctc var1, cti var2) {
      crn var3 = new crn();
      var3.setMnemonicRightPaddingLength(1);
      bat var4 = new bat();
      abg var5 = this.q(var1.getCode(), var2);
      var3.setCodeUnit(var5);
      var3.formatInstruction(0L, var1, var4);
      return var4.getCurrentLine().getText().toString();
   }

   private abg q(byte[] var1, cti var2) {
      PropertyDefinitionManager var3 = new PropertyDefinitionManager();
      abg var4 = new abg("proc", "bin", new BytesInput(var1), new wq(false, var3, null), new Artifact("emptyArtifact", new BytesInput(new byte[0])), var3);
      var4.setVirtualImageBase(0L);
      var4.setProcessor(var2);
      var4.setMemory(new azm(32));
      var4.process();
      return var4;
   }

   @Override
   public Map q(IUnit var1) {
      return null;
   }
}
