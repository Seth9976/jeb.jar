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
import com.pnfsoftware.jebglobal.acv;
import com.pnfsoftware.jebglobal.acw;
import com.pnfsoftware.jebglobal.ada;
import com.pnfsoftware.jebglobal.adb;
import com.pnfsoftware.jebglobal.adc;
import com.pnfsoftware.jebglobal.add;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

public class Vj {
   private static final ILogger q = GlobalLog.getLogger(Vj.class);
   private static final String RF = "processor";
   private static final String xK = "target_file";
   private static final String Dw = "extensions";
   private static final String Uv = "output_file_name";
   private static final String oW = "package_name";
   private static final String gO = "package_version";
   private static final String nf = "package_description";
   private static final String gP = "package_author";
   private static final String za = "compiler_name";
   private static final String lm = "solver_mode";
   private static final String zz = "routines_blacklist";
   private static final String JY = "comments";
   private static final String HF = "use_external_unmangler";
   private static final String LK = "confirm_removals";
   private static final String io = "debug_mode";
   private static final String qa = "called_routines_blacklist";
   private static final String Hk = "compiler";
   private static final String Me = "incomplete_routines";
   private static final String PV = "very_small_force_sign";
   private static final String oQ = "keep_low_confidence_sigs";
   private static final String xW = "library_id";
   private static final String KT = "uuid";
   private static final String Gf = "input_folder_name";
   private static final String Ef = "strategy";
   private static final String cC = "features";
   private static final String sH = "attributes";
   private static final String CE = "typelibs_folder";

   public static void q(oL var0, File var1) throws IOException {
      StringBuilder var2 = new StringBuilder();
      Strings.ff(var2, "; automatic configuration dump -- generated on: %s", TimeFormatter.formatTimestamp(System.currentTimeMillis()));
      var2.append(Strings.LINESEP);
      if (var0.Gf() != null) {
         q(var2, "input_folder_name", var0.Gf());
      }

      q(var2, "processor", var0.Uv().toString());
      if (var0.oW() != null) {
         q(var2, "target_file", var0.oW());
      }

      if (!var0.gO().isEmpty()) {
         q(var2, "extensions", Strings.join(",", var0.gO()));
      }

      q(var2, "output_file_name", var0.nf());
      if (var0.CE() != null) {
         q(var2, "typelibs_folder", var0.CE());
      }

      q(var2, "package_name", var0.gP());
      q(var2, "package_version", Strings.ff("%d", var0.za()));
      if (var0.lm() != null) {
         q(var2, "package_description", var0.lm());
      }

      if (var0.zz() != null) {
         q(var2, "package_author", var0.zz());
      }

      if (var0.JY() != null) {
         q(var2, "compiler_name", var0.JY());
      }

      q(var2, "strategy", var0.Ef().getName());
      if (var0.cC() != null && !var0.cC().isEmpty()) {
         q(var2, "features", Strings.join(",", var0.cC()));
      }

      if (var0.sH() != null && !var0.sH().isEmpty()) {
         q(var2, "attributes", Strings.join(",", var0.sH()));
      }

      q(var2, "solver_mode", Strings.ff("%d", var0.HF()));
      if (!var0.KT().isEmpty()) {
         StringBuilder var3 = new StringBuilder();

         for (Entry var5 : var0.KT().entrySet()) {
            for (String var7 : (Set)var5.getValue()) {
               var3.append((String)var5.getKey());
               var3.append(":");
               var3.append(var7);
               var3.append(",");
            }
         }

         var3.deleteCharAt(var3.length() - 1);
         q(var2, "routines_blacklist", var3.toString());
      }

      if (!var0.LK().isEmpty()) {
         q(var2, "comments", Strings.join(",", var0.LK()));
      }

      q(var2, "use_external_unmangler", Strings.ff("%b", var0.io()));
      q(var2, "confirm_removals", Strings.ff("%b", var0.qa()));
      q(var2, "debug_mode", Strings.ff("%b", var0.Hk()));
      if (!var0.q().isEmpty()) {
         q(var2, "called_routines_blacklist", Strings.join(",", var0.q()));
      }

      if (var0.Me() != null) {
         q(var2, "compiler", var0.Me().getName());
      }

      if (!var0.RF().isEmpty()) {
         StringBuilder var8 = new StringBuilder();

         for (Couple var10 : var0.RF()) {
            var8.append((String)var10.getFirst());
            var8.append(":");
            var8.append(var10.getSecond());
            var8.append(",");
         }

         var8.deleteCharAt(var8.length() - 1);
         q(var2, "incomplete_routines", var8.toString());
      }

      if (!var0.xK().isEmpty()) {
         q(var2, "very_small_force_sign", Strings.joinv(",", var0.xK().iterator()));
      }

      q(var2, "keep_low_confidence_sigs", Strings.ff("%b", var0.PV()));
      if (var0.oQ() != null) {
         q(var2, "library_id", var0.oQ().toString());
      }

      q(var2, "uuid", Strings.ff("%d", var0.xW()));
      IO.writeFile(var1, var2.toString());
   }

   private static void q(StringBuilder var0, String var1, String var2) {
      var0.append(var1);
      var0.append("=");
      var0.append(var2);
      var0.append(Strings.LINESEP);
   }

   public static oL q(File var0) throws SiglibException {
      try {
         return q(Strings.decodeLocal(IO.readFile(var0)));
      } catch (IOException var2) {
         throw new SiglibException(var2);
      }
   }

   public static oL q(String var0) throws SiglibException {
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
                           var18 = xK(var35);
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
                           var23 = RF(var35);
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
                           q.warn("Line %d: Skipping unknown entry: %s", var28, var34);
                     }
                  } catch (Exception var45) {
                     throw new SiglibException(Strings.ff("Line %d: Invalid entry: %s: %s", var28, var34, var35), var45);
                  }
               }
            }
         }
      }

      oL var46 = new oL();
      if (var23 != null) {
         var46.q(var23);
      } else if (var46.Ef() instanceof com.pnfsoftware.jeb.corei.parsers.asm.nativesig.eo) {
         for (NativeFeatureSignerID var52 : var24) {
            ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.eo)var46.Ef()).q(var52);
         }

         for (NativeAttributeSignerID var53 : var25) {
            ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.eo)var46.Ef()).q(var53);
         }
      }

      if (!var24.isEmpty()) {
         var46.Uv(var24);
      }

      if (!var25.isEmpty()) {
         var46.oW(var25);
      }

      var46.lm(var26);
      var46.za(var1);
      var46.q(var2);
      var46.RF(var3);
      var46.RF((List)var4);
      var46.xK(var5);
      var46.Dw(var6);
      var46.q(var7);
      var46.Uv(var8);
      var46.oW(var9);
      var46.gO(var10);
      var46.RF(var11);

      for (Entry var54 : var12.entrySet()) {
         for (String var58 : (Set)var54.getValue()) {
            var46.q((String)var54.getKey(), var58);
         }
      }

      var46.xK((List)var13);
      var46.q(var14);
      var46.RF(var15);
      var46.xK(var16);
      var46.Dw((List)var17);
      var46.q((List)var27);
      var46.q(var18);

      for (Couple var55 : var19) {
         var46.q((String)var55.getFirst(), (Integer)var55.getSecond());
      }

      var46.Dw(var20);
      var46.q(var21);
      var46.xK(var22);
      String var51 = q(var46);
      if (!var51.isEmpty()) {
         throw new SiglibException(var51);
      } else {
         return var46;
      }
   }

   private static ISigningStrategy RF(String var0) throws SiglibException {
      switch (var0) {
         case "CUSTOM":
            return new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.eo();
         case "SIZE_BASED":
            return new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.bK();
         case "STRICT":
            return new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.tl();
         default:
            throw new SiglibException("unknown strategy");
      }
   }

   private static ICompiler xK(String var0) throws SiglibException {
      switch (var0) {
         case "unknown":
            return new ada();
         case "unknown (Linux)":
            return new adb();
         case "Android ART (dex2oat)":
            return new acv();
         case "Android NDK compiler":
            return new acw();
         case "unknown (Windows)":
            return new adc();
         case "Microsoft Visual Studio":
            return new add();
         default:
            throw new SiglibException("unknown compiler name");
      }
   }

   public static String q(oL var0) {
      StringBuilder var1 = new StringBuilder();
      if (var0.Uv() == null) {
         var1.append("processor type is not specified");
      }

      if (var0.nf() == null || var0.nf().isEmpty()) {
         var1.append(" output file name is not specified");
      }

      if (var0.xW() == 0) {
         var1.append(" UUID is not specified");
      }

      if (var0.CE() == null) {
         var1.append(" typelibs folder is not specified");
      } else if (!new File(var0.CE()).isDirectory()) {
         var1.append(" typelibs path is not an existing folder");
      }

      if (var0.gP() == null || var0.gP().isEmpty()) {
         var1.append(" package name is not specified");
      }

      if (var0.cC().isEmpty() && var0.Ef() == null) {
         var1.append(" features and strategy are not specified");
      }

      if (var0.cC().isEmpty() && var0.Ef() instanceof com.pnfsoftware.jeb.corei.parsers.asm.nativesig.eo) {
         var1.append(" features should be specified with custom strategy");
      }

      if (!var0.cC().isEmpty()) {
         if (!(var0.Ef() instanceof com.pnfsoftware.jeb.corei.parsers.asm.nativesig.eo)) {
            var1.append(" either specify a non custom strategy *or* select particular features");
         }

         if (!var0.cC().contains(NativeFeatureSignerID.ROUTINE_SIZE)) {
            Strings.ff(var1, " %s feature needs to be included in signatures", NativeFeatureSignerID.ROUTINE_SIZE);
         }
      }

      return var1.toString();
   }
}
