package com.pnfsoftware.jeb.corei.parsers.asm.type.tlgen;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.bcn;
import com.pnfsoftware.jebglobal.bcp;
import java.io.IOException;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

@Deprecated
public class HeaderParser {
   private static final ILogger q = GlobalLog.getLogger(HeaderParser.class);

   public static void main(String[] var0) {
      String var1;
      if (var0.length == 0) {
         String var2 = "C:\\Users\\nicol\\jeb2\\jeb3-core\\core\\testdata\\antlr\\demo\\";
         String var3 = "../sample1-clean.h";
         var1 = var2 + var3;
      } else {
         var1 = var0[0];
      }

      try {
         ANTLRFileStream var7 = new ANTLRFileStream(var1);
         bcn var8 = new bcn(var7);
         CommonTokenStream var4 = new CommonTokenStream(var8);
         bcp var5 = new bcp(var4);
         var5.Jf();
      } catch (IOException var6) {
         q.catching(var6);
      }
   }

   static {
      GlobalLog.addDestinationStream(System.out);
   }
}
