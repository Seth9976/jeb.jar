package com.pnfsoftware.jeb.corei.testbed;

import com.pnfsoftware.jeb.core.Artifact;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.properties.impl.PropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.corei.parsers.x86.Or;
import com.pnfsoftware.jeb.corei.parsers.x86.nA;
import com.pnfsoftware.jeb.corei.parsers.x86.vh;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.C;
import com.pnfsoftware.jebglobal.XR;
import com.pnfsoftware.jebglobal.awp;
import com.pnfsoftware.jebglobal.axv;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class cq implements K {
   private static final ILogger pC = GlobalLog.getLogger(cq.class);
   private String A = "X86";

   @Override
   public String pC() {
      return this.A;
   }

   @Override
   public int A() {
      return 10;
   }

   @Override
   public Map pC(String var1) throws IOException {
      Or var2 = new Or(32);
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
            vh var9 = this.pC(var8, var2);
            var4.put(var8, Strings.ff("%s", this.pC(var9, var2)));
         } catch (Exception var10) {
            var4.put(var8, "UNTRANSLATED");
         }

         var5 += var6;
      }

      return var4;
   }

   protected vh pC(String var1, Or var2) {
      try {
         byte[] var3 = Formatter.hexStringToByteArray(var1.replaceAll("\\s+", ""));
         return (vh)var2.parseAt(var3, 0, var3.length);
      } catch (ProcessorException var4) {
         throw new RuntimeException(var4);
      }
   }

   private String pC(vh var1, Or var2) {
      nA var3 = new nA();
      var3.setMnemonicRightPaddingLength(1);
      axv var4 = new axv();
      C var5 = this.pC(var1.getCode(), var2);
      var3.setCodeUnit(var5);
      var3.formatInstruction(0L, var1, var4);
      return var4.getCurrentLine().getText().toString();
   }

   private C pC(byte[] var1, Or var2) {
      PropertyDefinitionManager var3 = new PropertyDefinitionManager();
      C var4 = new C("proc", "bin", new BytesInput(var1), new XR(false, var3, null), new Artifact("emptyArtifact", new BytesInput(new byte[0])), var3);
      var4.setVirtualImageBase(0L);
      var4.setProcessor(var2);
      var4.setMemory(new awp(32));
      var4.process();
      return var4;
   }

   @Override
   public Map pC(IUnit var1) {
      return null;
   }
}
