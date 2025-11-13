package com.pnfsoftware.jeb.corei.parsers.asm.type.axgen;

import com.pnfsoftware.jeb.util.io.DirectByteArrayOutputStream;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.SerializationManager;
import com.pnfsoftware.jeb.util.serialization.Serializer;
import com.pnfsoftware.jebglobal.cuu;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class AXGen {
   private static final ILogger RF;
   static boolean q;

   static void q(Object var0) {
   }

   static void q(String var0, Object... var1) {
   }

   public static void main(String[] var0) throws IOException {
      File var1 = new File(System.getenv("JEB_DEVHOME"));
      String var2 = "win32";
      File var3 = new File(var1, "jeb3-core/typelibs_src/api_extensions/axdb." + var2 + ".txt");
      File var4 = new File(var1, "jeb3-core/core/src/com/pnfsoftware/jeb/core/assets/axdb." + var2 + ".bin");
      if (q) {
         var3 = new File(var1, "jeb3-core/typelibs_src/api_extensions/temp/axdb.test.txt");
      }

      Object[] var10000 = new Object[]{var3};
      tw var5 = new tw(var3);
      var5.RF();
      oM var6 = new oM("glibc", null, null);
      ej var7 = new ej(var6, var5.q());
      var10000 = new Object[]{var7.Uv()};
      cuu var8 = cuu.q();
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

      RF = GlobalLog.getLogger(tw.class, 30);
      q = false;
   }
}
