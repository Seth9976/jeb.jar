package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.explorer;

import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeSourceUnit;
import com.pnfsoftware.jeb.core.util.DecompilerHelper;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.abg;
import com.pnfsoftware.jebglobal.aem;
import com.pnfsoftware.jebglobal.agp;
import com.pnfsoftware.jebglobal.axp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DecompilerExplorer {
   private static final ILogger q = GlobalLog.getLogger(DecompilerExplorer.class);
   private static List RF = new ArrayList();
   private static String xK = null;
   private static String Dw = null;
   private static String Uv = null;

   public static void main(String[] var0) throws JebException, IOException {
      if (var0.length == 2) {
         Dw = var0[0];
         xK = Dw;
         Uv = var0[1];
      } else if (var0.length == 1) {
         Dw = var0[0];
         xK = Dw;
      }

      byte var1 = 2;
      boolean var2 = true;
      String var3 = "";
      if (var1 == 0) {
         var3 = "_no_optis";
      } else if (var1 == 1) {
         var3 = "_no_loop_optis";
      } else if (var1 == 10) {
         var3 = "_no_loop_no_extra_return_optis";
      } else if (var1 == 2) {
         var3 = "_all_optis";
      }

      if (Dw != null && Uv == null) {
         for (File var7 : new File(Dw).listFiles()) {
            if (!var7.getName().endsWith(".jdb2") && !var7.getName().endsWith(".txt")) {
               q(Dw, var7.getName(), var2, var3);
            }
         }
      } else if (Dw != null && Uv != null) {
         q(Dw, Uv, var2, var3);
      } else {
         q.warn("No file to process");
      }
   }

   private static boolean q(String var0, String var1, boolean var2, String var3) throws JebException, IOException {
      Object[] var10000 = new Object[]{var1};
      GlobalLog.addGlobalFilter("com.pnfsoftware.jeb.corei.parsers.asm.analyzer", Integer.MAX_VALUE);
      GlobalLog.addGlobalFilter("com.pnfsoftware.jeb.corei.parsers.elf", Integer.MAX_VALUE);
      GlobalLog.addGlobalFilter("com.pnfsoftware.jeb.corei.parsers.winpe", Integer.MAX_VALUE);
      GlobalLog.addGlobalFilter("com.pnfsoftware.jeb.corei.parsers.GenericProcessor", Integer.MAX_VALUE);
      qV var4 = new qV(var0, var1, var2, var3);
      List var5 = var4.q();
      if (var5 != null && !var5.isEmpty()) {
         abg var6 = (abg)UnitUtil.findChildByType((IUnit)var5.get(0), abg.class, false, 0);
         if (var6 == null) {
            var10000 = new Object[0];
            return false;
         } else if (!var6.process()) {
            var10000 = new Object[0];
            return false;
         } else {
            INativeDecompilerUnit var7 = (INativeDecompilerUnit)DecompilerHelper.getDecompiler(var6);
            nI var8 = new nI();
            HashMap var9 = new HashMap();
            List var10 = var6.getInternalMethods();
            if (var10.size() > 400) {
               var10000 = new Object[]{var10.size()};
               return false;
            } else {
               int var11 = 1;

               for (axp var13 : var10) {
                  var10000 = new Object[]{var13.getName(true), var11, var10.size()};
                  var11++;
                  if (RF.contains(var13.getName(true))) {
                     var10000 = new Object[0];
                     var9.put(var13.getAddress(), null);
                  } else {
                     if (var13.oW().getCFG().getEffectiveSize() > 4096) {
                        var10000 = new Object[]{var13.oW().getCFG().getEffectiveSize()};
                     }

                     boolean var15 = false;

                     INativeSourceUnit var14;
                     try {
                        GlobalLog.addGlobalFilter("", Integer.MAX_VALUE);
                        var14 = var7.getDecompiledUnit(var13.getAddress());
                        if (var14 == null) {
                           var14 = var7.decompileToUnit(var13.getAddress());
                           var15 = true;
                        }

                        GlobalLog.removeGlobalFilter("");
                     } catch (RuntimeException var20) {
                        GlobalLog.removeGlobalFilter("");
                        var10000 = new Object[]{var20.getMessage()};
                        Nt var17 = new Nt();
                        var17.q(var20.getMessage());
                        ArrayList var18 = new ArrayList();
                        var18.add(var17);
                        var9.put(var13.getAddress(), var18);
                        continue;
                     }

                     if (var14 == null) {
                        var10000 = new Object[0];
                        var9.put(var13.getAddress(), null);
                     } else {
                        if (((aem)var14).getASTItem() instanceof agp) {
                           agp var16 = (agp)((aem)var14).getASTItem();
                           var8.q(var16);
                           List var26 = var8.q();
                           var9.put(var13.getAddress(), var26);
                           var8.RF();
                        }

                        var10000 = new Object[]{var15 ? "yes" : "no"};
                     }
                  }
               }

               if (var2) {
                  var4.RF();
               }

               String var21 = var1 + var3 + ".txt";
               File var22 = new File(xK, var21);
               StringBuilder var23 = new StringBuilder();

               for (String var25 : var9.keySet()) {
                  var23.append("== Routine ").append(var25);
                  var23.append(System.getProperty("line.separator"));
                  if (var9.get(var25) == null) {
                     var23.append("Decompilation failed!");
                     var23.append(System.getProperty("line.separator"));
                  } else if (((List)var9.get(var25)).size() == 0) {
                     var23.append("No results");
                     var23.append(System.getProperty("line.separator"));
                  } else {
                     for (ej var28 : (List)var9.get(var25)) {
                        if (var28.RF() != null) {
                           var23.append(var28.RF());
                        }

                        if (var28.q() != null) {
                           var23.append(var28.q());
                        }
                     }
                  }
               }

               var10000 = new Object[]{var23.toString()};

               try {
                  IO.writeFile(var22, var23.toString());
               } catch (IOException var19) {
                  q.catching(var19);
               }

               return true;
            }
         }
      } else {
         var10000 = new Object[0];
         return false;
      }
   }

   static {
      GlobalLog.addDestinationStream(System.out);
   }
}
