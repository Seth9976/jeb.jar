package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.LibraryID;
import com.pnfsoftware.jeb.core.units.code.asm.sig.ISigningStrategy;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeAttributeSignerID;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeFeatureSignerID;
import com.pnfsoftware.jeb.core.units.code.asm.sig.SiglibException;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TimeFormatter;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.abd;
import com.pnfsoftware.jebglobal.abe;
import com.pnfsoftware.jebglobal.abi;
import com.pnfsoftware.jebglobal.abj;
import com.pnfsoftware.jebglobal.abk;
import com.pnfsoftware.jebglobal.abl;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

public class qt {
   private static final ILogger pC = GlobalLog.getLogger(qt.class);

   public static void pC(HE var0, File var1) throws IOException {
      StringBuilder var2 = new StringBuilder();
      Strings.ff(var2, "; automatic configuration dump -- generated on: %s", TimeFormatter.formatTimestamp(System.currentTimeMillis()));
      var2.append(Strings.LINESEP);
      if (var0.rl() != null) {
         pC(var2, "input_folder_name", var0.rl());
      }

      pC(var2, "processor", var0.wS().toString());
      if (var0.UT() != null) {
         pC(var2, "target_file", var0.UT());
      }

      if (!var0.E().isEmpty()) {
         pC(var2, "extensions", Strings.join(",", var0.E()));
      }

      pC(var2, "output_file_name", var0.sY());
      if (var0.Er() != null) {
         pC(var2, "typelibs_folder", var0.Er());
      }

      pC(var2, "package_name", var0.ys());
      pC(var2, "package_version", Strings.ff("%d", var0.ld()));
      if (var0.gp() != null) {
         pC(var2, "package_description", var0.gp());
      }

      if (var0.oT() != null) {
         pC(var2, "package_author", var0.oT());
      }

      if (var0.fI() != null) {
         pC(var2, "compiler_name", var0.fI());
      }

      pC(var2, "strategy", var0.z().getName());
      if (var0.Ek() != null && !var0.Ek().isEmpty()) {
         pC(var2, "features", Strings.join(",", var0.Ek()));
      }

      if (var0.hK() != null && !var0.hK().isEmpty()) {
         pC(var2, "attributes", Strings.join(",", var0.hK()));
      }

      pC(var2, "solver_mode", Strings.ff("%d", var0.WR()));
      if (!var0.Ab().isEmpty()) {
         StringBuilder var3 = new StringBuilder();

         for (Entry var5 : var0.Ab().entrySet()) {
            for (String var7 : (Set)var5.getValue()) {
               var3.append((String)var5.getKey());
               var3.append(":");
               var3.append(var7);
               var3.append(",");
            }
         }

         var3.deleteCharAt(var3.length() - 1);
         pC(var2, "routines_blacklist", var3.toString());
      }

      if (!var0.NS().isEmpty()) {
         pC(var2, "comments", Strings.join(",", var0.NS()));
      }

      pC(var2, "use_external_unmangler", Strings.ff("%b", var0.vP()));
      pC(var2, "confirm_removals", Strings.ff("%b", var0.xC()));
      pC(var2, "debug_mode", Strings.ff("%b", var0.ED()));
      if (!var0.pC().isEmpty()) {
         pC(var2, "called_routines_blacklist", Strings.join(",", var0.pC()));
      }

      if (var0.Sc() != null) {
         pC(var2, "compiler", var0.Sc().getName());
      }

      if (!var0.A().isEmpty()) {
         StringBuilder var8 = new StringBuilder();

         for (Couple var10 : var0.A()) {
            var8.append((String)var10.getFirst());
            var8.append(":");
            var8.append(var10.getSecond());
            var8.append(",");
         }

         var8.deleteCharAt(var8.length() - 1);
         pC(var2, "incomplete_routines", var8.toString());
      }

      if (!var0.kS().isEmpty()) {
         pC(var2, "very_small_force_sign", Strings.joinv(",", var0.kS().iterator()));
      }

      pC(var2, "keep_low_confidence_sigs", Strings.ff("%b", var0.ah()));
      if (var0.eP() != null) {
         pC(var2, "library_id", var0.eP().toString());
      }

      pC(var2, "uuid", Strings.ff("%d", var0.UO()));
      IO.writeFile(var1, var2.toString());
   }

   private static void pC(StringBuilder var0, String var1, String var2) {
      var0.append(var1);
      var0.append("=");
      var0.append(var2);
      var0.append(Strings.LINESEP);
   }

   public static HE pC(File var0) throws SiglibException {
      try {
         return pC(Strings.decodeLocal(IO.readFile(var0)));
      } catch (IOException var2) {
         throw new SiglibException(var2);
      }
   }

   public static HE pC(String var0) throws SiglibException {
      String var1 = null;
      ProcessorType var2 = null;
      String var3 = null;
      Object var4 = new ArrayList();
      String var5 = null;
      String var6 = null;
      int var7 = 0;
      String var8 = null;
      String var9 = null;
      String var10 = null;
      int var11 = 0;
      HashMap var12 = new HashMap();
      Object var13 = new ArrayList();
      boolean var14 = true;
      boolean var15 = true;
      boolean var16 = true;
      Object var17 = new ArrayList();
      ICompiler var18 = null;
      HashSet var19 = new HashSet();
      boolean var20 = false;
      LibraryID var21 = null;
      int var22 = 0;
      ISigningStrategy var23 = null;
      ArrayList var24 = new ArrayList();
      ArrayList var25 = new ArrayList();
      String var26 = null;
      Object var27 = new ArrayList();
      int var28 = 0;

      for (String var32 : Strings.splitLines(var0)) {
         var28++;
         var32 = Strings.trim(var32);
         if (!var32.isEmpty() && !var32.startsWith(";")) {
            String[] var33 = var32.split("=");
            if (var33.length == 2) {
               String var34 = var33[0].trim();
               String var35 = var33[1].trim();
               if (!var35.isEmpty()) {
                  try {
                     switch (var34) {
                        case "typelibs_folder":
                           var26 = var35;
                           break;
                        case "input_folder_name":
                           var1 = var35;
                           break;
                        case "processor":
                           var2 = ProcessorType.valueOf(var35.toUpperCase());
                           break;
                        case "target_file":
                           var3 = var35;
                           break;
                        case "extensions":
                           var4 = Arrays.asList(var35.split(","));
                           break;
                        case "output_file_name":
                           var5 = var35;
                           break;
                        case "package_name":
                           var6 = var35;
                           break;
                        case "package_version":
                           var7 = Integer.parseInt(var35);
                           break;
                        case "package_description":
                           var8 = var35;
                           break;
                        case "package_author":
                           var9 = var35;
                           break;
                        case "compiler_name":
                           var10 = var35;
                           break;
                        case "solver_mode":
                           var11 = Integer.parseInt(var35);
                           break;
                        case "routines_blacklist":
                           for (String var70 : var35.split(",")) {
                              String var71 = var70.split(":")[0];
                              String var72 = var70.split(":")[1];
                              Object var44 = (Set)var12.get(var71);
                              if (var44 == null) {
                                 var44 = new HashSet();
                                 var12.put(var71, var44);
                              }

                              var44.add(var72);
                           }
                           break;
                        case "comments":
                           var13 = Arrays.asList(var35.split(","));
                           break;
                        case "use_external_unmangler":
                           var14 = Boolean.parseBoolean(var35);
                           break;
                        case "confirm_removals":
                           var15 = Boolean.parseBoolean(var35);
                           break;
                        case "debug_mode":
                           var16 = Boolean.parseBoolean(var35);
                           break;
                        case "called_routines_blacklist":
                           var17 = Arrays.asList(var35.split(","));
                           break;
                        case "compiler":
                           var18 = kS(var35);
                           break;
                        case "incomplete_routines":
                           for (String var69 : var35.split(",")) {
                              String var42 = var69.split(":")[0];
                              int var43 = Integer.parseInt(var69.split(":")[1]);
                              var19.add(new Couple(var42, var43));
                           }
                           break;
                        case "very_small_force_sign":
                           var27 = Arrays.asList(var35.split(","));
                           break;
                        case "keep_low_confidence_sigs":
                           var20 = Boolean.parseBoolean(var35);
                           break;
                        case "library_id":
                           var21 = LibraryID.valueOf(var35);
                           break;
                        case "uuid":
                           var22 = Integer.parseInt(var35);
                           break;
                        case "strategy":
                           var23 = A(var35);
                           break;
                        case "features":
                           for (String var68 : var35.split(",")) {
                              var24.add(NativeFeatureSignerID.valueOf(var68));
                           }
                           break;
                        case "attributes":
                           for (String var41 : var35.split(",")) {
                              var25.add(NativeAttributeSignerID.valueOf(var41));
                           }
                           break;
                        default:
                           pC.warn("Line %d: Skipping unknown entry: %s", var28, var34);
                     }
                  } catch (Exception var45) {
                     throw new SiglibException(Strings.ff("Line %d: Invalid entry: %s: %s", var28, var34, var35), var45);
                  }
               }
            }
         }
      }

      HE var46 = new HE();
      if (var23 != null) {
         var46.pC(var23);
      } else if (var46.z() instanceof com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Av) {
         for (NativeFeatureSignerID var52 : var24) {
            ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Av)var46.z()).pC(var52);
         }

         for (NativeAttributeSignerID var53 : var25) {
            ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Av)var46.z()).pC(var53);
         }
      }

      if (!var24.isEmpty()) {
         var46.UT(var24);
      }

      if (!var25.isEmpty()) {
         var46.E(var25);
      }

      var46.ys(var26);
      var46.sY(var1);
      var46.pC(var2);
      var46.pC(var3);
      var46.A((List)var4);
      var46.A(var5);
      var46.kS(var6);
      var46.pC(var7);
      var46.wS(var8);
      var46.UT(var9);
      var46.E(var10);
      var46.A(var11);

      for (Entry var54 : var12.entrySet()) {
         for (String var58 : (Set)var54.getValue()) {
            var46.pC((String)var54.getKey(), var58);
         }
      }

      var46.kS((List)var13);
      var46.pC(var14);
      var46.A(var15);
      var46.kS(var16);
      var46.wS((List)var17);
      var46.pC((List)var27);
      var46.pC(var18);

      for (Couple var55 : var19) {
         var46.pC((String)var55.getFirst(), (Integer)var55.getSecond());
      }

      var46.wS(var20);
      var46.pC(var21);
      var46.kS(var22);
      String var51 = pC(var46);
      if (!var51.isEmpty()) {
         throw new SiglibException(var51);
      } else {
         return var46;
      }
   }

   private static ISigningStrategy A(String var0) throws SiglibException {
      switch (var0) {
         case "CUSTOM":
            return new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Av();
         case "SIZE_BASED":
            return new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.vi();
         case "STRICT":
            return new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.p();
         default:
            throw new SiglibException("unknown strategy");
      }
   }

   private static ICompiler kS(String var0) throws SiglibException {
      switch (var0) {
         case "unknown":
            return new abi();
         case "unknown (Linux)":
            return new abj();
         case "Android ART (dex2oat)":
            return new abd();
         case "Android NDK compiler":
            return new abe();
         case "unknown (Windows)":
            return new abk();
         case "Microsoft Visual Studio":
            return new abl();
         default:
            throw new SiglibException("unknown compiler name");
      }
   }

   public static String pC(HE var0) {
      StringBuilder var1 = new StringBuilder();
      if (var0.wS() == null) {
         var1.append("processor type is not specified");
      }

      if (var0.sY() == null || var0.sY().isEmpty()) {
         var1.append(" output file name is not specified");
      }

      if (var0.UO() == 0) {
         var1.append(" UUID is not specified");
      }

      if (var0.Er() == null) {
         var1.append(" typelibs folder is not specified");
      } else if (!new File(var0.Er()).isDirectory()) {
         var1.append(" typelibs path is not an existing folder");
      }

      if (var0.ys() == null || var0.ys().isEmpty()) {
         var1.append(" package name is not specified");
      }

      if (var0.Ek().isEmpty() && var0.z() == null) {
         var1.append(" features and strategy are not specified");
      }

      if (var0.Ek().isEmpty() && var0.z() instanceof com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Av) {
         var1.append(" features should be specified with custom strategy");
      }

      if (!var0.Ek().isEmpty()) {
         if (!(var0.z() instanceof com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Av)) {
            var1.append(" either specify a non custom strategy *or* select particular features");
         }

         if (!var0.Ek().contains(NativeFeatureSignerID.ROUTINE_SIZE)) {
            Strings.ff(var1, " %s feature needs to be included in signatures", NativeFeatureSignerID.ROUTINE_SIZE);
         }
      }

      return var1.toString();
   }
}
