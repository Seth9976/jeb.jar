package com.pnfsoftware.jeb.corei.parsers.asm.type.axgen;

import com.pnfsoftware.jeb.util.io.DirectByteArrayOutputStream;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.SerializationManager;
import com.pnfsoftware.jeb.util.serialization.Serializer;
import com.pnfsoftware.jebglobal.ckh;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class AXGen {
   private static final ILogger A;
   static boolean pC;

   public static void main(String[] var0) throws IOException {
      File var1 = new File(System.getenv("JEB_DEVHOME"));
      String var2 = "win32";
      File var3 = new File(var1, "jeb3-core/typelibs_src/api_extensions/axdb." + var2 + ".txt");
      File var4 = new File(var1, "jeb3-core/core/src/com/pnfsoftware/jeb/core/assets/axdb." + var2 + ".bin");
      if (pC) {
         var3 = new File(var1, "jeb3-core/typelibs_src/api_extensions/temp/axdb.test.txt");
      }

      Object[] var10000 = new Object[]{var3};
      DH var5 = new DH(var3);
      var5.A();
      Ws var6 = new Ws("glibc", null, null);
      K var7 = new K(var6, var5.pC());
      var10000 = new Object[]{var7.pC()};
      ckh var8 = ckh.pC();
      SerializationManager var9 = new SerializationManager(var8);
      DirectByteArrayOutputStream var10 = new DirectByteArrayOutputStream();
      Serializer var11 = var9.getSerializer(var10);
      var11.serialize(var6);
      var11.serialize(var7);
      var11.close();
      if (var4 != null) {
         IO.writeFile(var4, var10.getRawBytes(), 0, var10.size());
         var10000 = new Object[]{var4};
      }
   }

   static {
      GlobalLog.addDestinationStream(System.out);

      try {
         String var0 = System.getProperty("java.io.tmpdir");
         File var1 = new File(var0, "ax-output.log");
         GlobalLog.addDestinationStream(new PrintStream(var1));
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }

      A = GlobalLog.getLogger(DH.class, 30);
      pC = false;
   }
}
