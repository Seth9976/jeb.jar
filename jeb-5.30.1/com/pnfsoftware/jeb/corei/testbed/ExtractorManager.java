package com.pnfsoftware.jeb.corei.testbed;

import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitIdentifier;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;

public class ExtractorManager {
   private static final ILogger q = GlobalLog.getLogger(ExtractorManager.class);
   private static final Map RF = new HashMap();

   public static void main(String[] var0) throws JebException, IOException {
      if (var0.length != 3) {
         Object[] var2 = new Object[0];
      } else {
         GlobalLog.addGlobalFilter("com.pnfsoftware.jeb.corei.parsers.asm.analyzer", Integer.MAX_VALUE);
         GlobalLog.addGlobalFilter("com.pnfsoftware.jeb.corei.parsers.elf", Integer.MAX_VALUE);
         GlobalLog.addGlobalFilter("com.pnfsoftware.jeb.corei.parsers.winpe", Integer.MAX_VALUE);
         GlobalLog.addGlobalFilter("com.pnfsoftware.jeb.corei.parsers.GenericProcessor", Integer.MAX_VALUE);
         GlobalLog.addGlobalFilter("com.pnfsoftware.jeb.core.properties.impl.PropertyManager", Integer.MAX_VALUE);
         nI var1 = (nI)RF.get(var0[0]);
         if (var1 == null) {
            Object[] var10000 = new Object[]{var0[0]};
         } else {
            if (var1.RF() == 0) {
               RF(var0[1], var0[2], var1);
            } else if (var1.RF() == 10) {
               q(var0[1], var0[2], var1);
            }
         }
      }
   }

   private static boolean q(String var0, String var1, nI var2) throws IOException {
      Map var3 = var2.q(var0);
      if (var3 != null) {
         Object[] var10000 = new Object[]{var2.q()};
         q(var1, var3, var2.q());
      } else {
         Object[] var4 = new Object[]{var2.q()};
      }

      return true;
   }

   private static boolean RF(String var0, String var1, nI var2) throws JebException, IOException {
      Object[] var10000 = new Object[]{var0};
      File var3 = new File(var0);
      oM var4 = new oM(var3.getParent(), var3.getName());
      IEnginesContext var5 = var4.RF();

      for (IUnitIdentifier var7 : var5.getUnitIdentifiers()) {
         if (Strings.isContainedIn(var7.getFormatType(), "x86", "x86_64", "arm", "arm64", "mips", "mips64")) {
            var5.setIdentifierEnabled(var7, false);
         }
      }

      List var9 = var4.q();
      if (var9.size() != 1) {
         var10000 = new Object[0];
         return false;
      } else {
         IUnit var10 = (IUnit)var9.get(0);
         Map var8 = var2.q(var10);
         if (var8 != null) {
            var10000 = new Object[]{var2.q()};
            q(var1, var8, var2.q());
         } else {
            var10000 = new Object[]{var2.q()};
         }

         return true;
      }
   }

   public static void q(String var0, Map var1, String var2) throws IOException {
      Object[] var10000 = new Object[0];
      JSONObject var3 = new JSONObject(var1);
      File var4 = new File(var0, Strings.ff("%s_JEB4.json", var2));
      IO.writeFile(var4, var3.toString().getBytes(StandardCharsets.UTF_8));
      var10000 = new Object[]{var4.getAbsolutePath()};
   }

   static {
      GlobalLog.addDestinationStream(System.out);
      ej var0 = new ej();
      RF.put(var0.q(), var0);
      Nt var1 = new Nt();
      RF.put(var1.q(), var1);
      CU var2 = new CU();
      RF.put(var2.q(), var2);
      eo var3 = new eo();
      RF.put(var3.q(), var3);
      Collections.unmodifiableMap(RF);
   }
}
