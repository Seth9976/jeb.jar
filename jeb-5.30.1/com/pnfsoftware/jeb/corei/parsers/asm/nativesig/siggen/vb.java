package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen;

import com.pnfsoftware.jeb.client.HeadlessClientContext;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.WellKnownUnitTypes;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CallGraphVertex;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReference;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IManglingEngine;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledData;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledRoutine;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeAttribute;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeFeature;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignaturePackage;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeFeatureSignerID;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignatureFlags;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignatureGenerator;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignaturePackageMetadata;
import com.pnfsoftware.jeb.core.units.code.asm.sig.SignatureTargetType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.DirectByteArrayOutputStream;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.SerializationException;
import com.pnfsoftware.jeb.util.serialization.SerializationManager;
import com.pnfsoftware.jeb.util.serialization.Serializer;
import com.pnfsoftware.jebglobal.aae;
import com.pnfsoftware.jebglobal.abg;
import com.pnfsoftware.jebglobal.acw;
import com.pnfsoftware.jebglobal.add;
import com.pnfsoftware.jebglobal.axn;
import com.pnfsoftware.jebglobal.axp;
import com.pnfsoftware.jebglobal.ayl;
import com.pnfsoftware.jebglobal.ayn;
import com.pnfsoftware.jebglobal.azh;
import com.pnfsoftware.jebglobal.azq;
import com.pnfsoftware.jebglobal.azr;
import com.pnfsoftware.jebglobal.azt;
import com.pnfsoftware.jebglobal.azv;
import com.pnfsoftware.jebglobal.azw;
import com.pnfsoftware.jebglobal.bad;
import com.pnfsoftware.jebglobal.bag;
import com.pnfsoftware.jebglobal.bah;
import com.pnfsoftware.jebglobal.bai;
import com.pnfsoftware.jebglobal.baj;
import com.pnfsoftware.jebglobal.bak;
import com.pnfsoftware.jebglobal.bao;
import com.pnfsoftware.jebglobal.bap;
import com.pnfsoftware.jebglobal.baq;
import com.pnfsoftware.jebglobal.cti;
import com.pnfsoftware.jebglobal.cuu;
import com.pnfsoftware.jebglobal.v;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class vb {
   private static final ILogger za = GlobalLog.getLogger(vb.class);
   public static final String q = "input";
   public static final String RF = "output";
   public static final String xK = "siglib_config.cfg";
   public static final String Dw = "siglib_config.bin";
   public static final String Uv = ".rtn_to_sign";
   private static bK lm = new bK();
   private static com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen.eo zz;
   private static PY JY;
   static final Set oW = new HashSet();
   public static final int gO = 5;
   public static final int nf = 30;
   public static final int gP = 600;
   private static final Set HF = new HashSet();
   private static final Set LK = new HashSet();
   private static final Map io = new HashMap();
   private static final Map qa = new HashMap();
   private static final Map Hk = new HashMap();
   private static final Set Me = new HashSet();
   private static final Map PV = new IdentityHashMap();
   private static ICompiler oQ;
   private static List xW = new ArrayList();
   private static Map KT = null;

   public static INativeSignaturePackage q(String var0, oL var1, boolean var2, boolean var3, boolean var4) throws JebException, IOException {
      String var5 = Vj.q(var1);
      if (!var5.isEmpty()) {
         throw new JebRuntimeException("Invalid config:" + var5);
      } else {
         String var6 = "input";
         if (var1.Gf() != null) {
            var6 = var1.Gf();
         }

         String var7 = var0.concat(File.separator).concat(var6);
         if (!new File(var7).exists()) {
            Object[] var10000 = new Object[]{var7};
            System.exit(0);
         }

         String var8 = var1.RF;
         ArrayList var9 = new ArrayList();
         if (var8 != null && !var8.equals("")) {
            throw new RuntimeException("TBI: target file config option");
         } else {
            for (File var11 : IO.listFiles(var7)) {
               if (!var1.xK.isEmpty()) {
                  for (String var13 : var1.xK) {
                     if (var11.getName().endsWith(var13)) {
                        var9.add(var11);
                     }
                  }
               } else {
                  var9.add(var11);
               }
            }

            if (var9.isEmpty()) {
               Object[] var72 = new Object[]{var7};
               System.exit(0);
            }

            Object[] var73 = new Object[]{var9};
            NativeSignaturePackageMetadata var23 = NativeSignaturePackageMetadata.create(var1.q, var1.Uv, var1.oQ, var1.oW, var1.gO, var1.nf, var1.qa, var1.PV);
            INativeSignaturePackage var24 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Vj(var23);
            oQ = var1.qa;

            for (File var30 : var9) {
               int var14 = var24.count();
               q(var30.getParent(), var30.getName(), var24, var1);
               var73 = new Object[]{var30.getName(), var24.count() - var14};
            }

            zz = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen.eo(
               var0,
               var2 ? com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen.eo.Dw : com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen.eo.q,
               var2 ? com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen.eo.xK : com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen.eo.q
            );
            zz.oW();
            if (var1.HF) {
               JY = new PY(var0, var2 ? PY.eo.RF : PY.eo.q);
               JY.Uv();
            }

            RF();
            var24 = oW(var24);
            q(var24, LK, !var1.Me);

            for (Set var31 : PV.values()) {
               ArrayList var37 = new ArrayList();

               for (INativeFeature var16 : var31) {
                  boolean var17 = false;
                  if (var16 instanceof bag) {
                     for (INativeFeature var19 : ((bag)var16).q()) {
                        if (LK.contains(((bah)var19).q())) {
                           var17 = true;
                           break;
                        }
                     }
                  }

                  if (var16 instanceof bah && LK.contains(((bah)var16).q())) {
                     var17 = true;
                  }

                  if (var17) {
                     var37.add(var16);
                  }
               }

               var31.removeAll(var37);
            }

            if (!var1.Me) {
               HashSet var28 = new HashSet();
               if (var1.HF) {
                  for (INativeSignature var38 : Me) {
                     JY.q(var38, null);
                  }

                  if (!JY.RF()) {
                     JY.Dw();
                     var73 = new Object[]{JY.xK(), File.separator, "removals.txt"};
                     System.exit(0);
                  }

                  for (INativeSignature var39 : Me) {
                     PY.eo var44 = JY.q(var39);
                     if (var44 == PY.eo.RF) {
                        var24.getSignatures().remove(var39);
                        var28.add(var39.getTargetName());
                     } else if (var44 != PY.eo.q) {
                        throw new RuntimeException("unknown removal status");
                     }
                  }

                  q(var24, var28, true);
               } else {
                  for (INativeSignature var40 : Me) {
                     var24.getSignatures().remove(var40);
                     var28.add(var40.getTargetName());
                  }

                  q(var24, var28, true);
               }
            }

            RF(var24);
            int var29 = 0;

            do {
               var73 = new Object[]{var29};
               var29++;
               xK(var24);
               Dw(var24);
               q(var24);
               RF(var24);
            } while (RF(var24, var1) || q(var24, var1));

            if (zz.q()) {
               for (INativeSignature var41 : var24.getSignatures()) {
                  List var45 = (List)Hk.get(var41);
                  if (var45 != null) {
                     ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV)var41).q(var45);
                  }
               }

               HashSet var36 = new HashSet();

               for (INativeSignature var46 : var24.getSignatures()) {
                  if (var46.getFlags().hasMeaningfulTargetName()) {
                     var36.add(var46.getTargetName());
                     if (var46.getAlternateNames() != null) {
                        var36.addAll(var46.getAlternateNames());
                     }
                  } else if (var46.getPossibleNames() != null) {
                     var36.addAll(var46.getPossibleNames());
                  }
               }

               com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Vj var43 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Vj(var24.getMetadata());

               for (INativeSignature var51 : var24.getSignatures()) {
                  ArrayList var55 = new ArrayList();
                  HashSet var59 = new HashSet();

                  for (INativeFeature var20 : ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var51).RF()) {
                     if (var20 instanceof bah) {
                        var59.add(((bah)var20).q());
                     }
                  }

                  for (INativeAttribute var69 : var51.getAttributes()) {
                     if (var69 instanceof azv) {
                        String var21 = (String)((azv)var69).q().getSecond();
                        if (var36.contains(var21) || var59.contains(var21)) {
                           continue;
                        }
                     }

                     var55.add(var69);
                  }

                  com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV var65 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV(
                     var51.getTargetName(),
                     ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var51).q(),
                     var55,
                     var51.getFlags(),
                     var51.getConfidenceLevel()
                  );
                  var65.q(var51.getAlternateNames());
                  var65.RF(var51.getPossibleNames());
                  var43.q(var65);
               }

               var24 = var43;

               for (INativeSignature var52 : var43.getSignatures()) {
                  String var56 = q(var52);
                  String var60 = var52.getTargetName();
                  lm.RF(var60, var56 != null ? var56 : "unknown", var52);
               }

               if (var4) {
                  lm.q(var43, var0);
                  lm.RF(var0);
                  JY.Dw();
                  var73 = new Object[]{JY.xK(), File.separator, "removals.txt"};
                  zz.Uv();
                  var73 = new Object[]{zz.Dw(), File.separator, "conflicts.txt"};
               }

               if (!var1.LK) {
                  com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Vj var49 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Vj(var43.getMetadata());

                  for (INativeSignature var57 : var43.getSignatures()) {
                     ArrayList var61 = new ArrayList();

                     for (INativeAttribute var70 : var57.getAttributes()) {
                        if (!(var70 instanceof azr) && !(var70 instanceof azq)) {
                           var61.add(var70);
                        }
                     }

                     com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV var67 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV(
                        var57.getTargetName(),
                        ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var57).q(),
                        var61,
                        var57.getFlags(),
                        var57.getConfidenceLevel()
                     );
                     var67.q(var57.getAlternateNames());
                     var67.RF(var57.getPossibleNames());
                     var49.q(var67);
                  }

                  var24 = var49;
               }

               if (var3) {
                  if (var24.count() > 0) {
                     cuu var50 = cuu.q();
                     SerializationManager var54 = new SerializationManager(var50);
                     DirectByteArrayOutputStream var58 = new DirectByteArrayOutputStream();
                     Serializer var62 = var54.getSerializer(var58);

                     try {
                        var62.serialize(var23);
                        var62.serialize(var24);
                        var62.close();
                     } catch (SerializationException var22) {
                        za.catching(var22);
                     }

                     File var68 = new File(var0.concat(File.separator).concat("output"));
                     if (!var68.exists()) {
                        IO.createDirectory(var68);
                     }

                     File var71 = new File(var0.concat(File.separator).concat("output"), var1.Dw + ".siglib");
                     IO.writeFile(var71, var58.getRawBytes(), 0, var58.size());
                  } else {
                     var73 = new Object[0];
                  }
               }
            }

            return var24;
         }
      }
   }

   private static String q(INativeSignature var0) {
      for (INativeAttribute var2 : var0.getAttributes()) {
         if (var2 instanceof azr) {
            return ((azr)var2).q();
         }
      }

      return null;
   }

   private static void RF() {
      if (oQ.isWindowsCompatible()) {
         xW.add(new ayl());
         xW.add(new ayn());
      } else if (oQ.isLinuxCompatible()) {
         xW.add(new azh());
      } else {
         xW.add(new ayl());
         xW.add(new ayn());
         xW.add(new azh());
      }
   }

   private static String q(String var0) {
      IUnmangledData var1 = null;

      for (IManglingEngine var3 : xW) {
         var1 = var3.unmangle(var0);
         if (var1 != null) {
            break;
         }
      }

      if (var1 != null) {
         String var4 = var1.getFull();
         if (var4 != null) {
            return var4;
         }

         if (var1 instanceof IUnmangledRoutine) {
            return ((IUnmangledRoutine)var1).getName();
         }
      }

      return oQ.isWindowsCompatible() ? RF(var0) : null;
   }

   private static void q(INativeSignaturePackage var0) {
      io.clear();
      qa.clear();

      for (INativeSignature var2 : var0.getSignatures()) {
         int var3 = RF(var2);

         for (INativeFeature var5 : ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var2).RF()) {
            if (var5 instanceof bah) {
               q(var5, var3);
            }
         }
      }
   }

   private static void q(INativeFeature var0, int var1) {
      String var2 = ((bah)var0).q();
      if (!io.containsKey(var2)) {
         io.put(var2, 1);
         if (RF(var1)) {
            qa.put(var2, 1);
         }
      } else {
         int var3 = (Integer)io.get(var2);
         io.put(var2, var3 + 1);
         if (RF(var1)) {
            qa.put(var2, var3 + 1);
         }
      }
   }

   private static void RF(INativeSignaturePackage var0) {
      for (INativeSignature var2 : var0.getSignatures()) {
         for (INativeSignature var4 : var0.getSignatures()) {
            if (var2 != var4) {
               String var5 = var2.getTargetName();
               if (var5.equals(var4.getTargetName())) {
                  HF.add(var5);
               }
            }
         }
      }
   }

   private static void q(INativeSignature var0, iZ var1) {
      String var2 = var0.getTargetName();
      String var3 = q(var2);
      if (var3 != null) {
         var1.q(var0, var3);
      }

      var1.q(
         var0,
         Strings.ff(
            "[%d CalledRoutineName - %d in small routines - unique name: %b]",
            io.containsKey(var2) ? (Integer)io.get(var2) : 0,
            qa.containsKey(var2) ? (Integer)qa.get(var2) : 0,
            !HF.contains(var2)
         )
      );
   }

   private static boolean q(INativeSignaturePackage var0, oL var1) {
      Object[] var10000 = new Object[0];
      var10000 = new Object[0];
      var10000 = new Object[0];
      ArrayList var2 = new ArrayList();

      for (INativeSignature var4 : var0.getSignatures()) {
         boolean var5 = false;

         for (Set var7 : var2) {
            INativeSignature var8 = (INativeSignature)var7.iterator().next();
            if (var4.matchExactly(var8)) {
               var7.add(var4);
               var5 = true;
               break;
            }
         }

         if (!var5) {
            HashSet var28 = new HashSet();
            var28.add(var4);
            var2.add(var28);
         }
      }

      ArrayList var22 = new ArrayList();

      for (Set var25 : var2) {
         if (var25.size() >= 2) {
            var22.add(var25);
         }
      }

      if (var22.isEmpty()) {
         return false;
      } else {
         HashSet var24 = new HashSet();

         for (Set var29 : var22) {
            oM var31 = new oM(var29, true);

            for (INativeSignature var9 : var31.q()) {
               q(var9, var31);
            }

            var24.add(var31);
            zz.q(var31, null);
         }

         if (!zz.RF()) {
            zz.Uv();
            var10000 = new Object[]{zz.Dw(), File.separator, "conflicts.txt"};
            System.exit(0);
         }

         HashSet var27 = new HashSet();
         HashMap var30 = new HashMap();

         for (oM var35 : var24) {
            Nt var37 = zz.q(var35);
            if (var37 instanceof vn) {
               int var39 = ((vn)var37).q();
               INativeSignature var42 = zz.q(var39);
               boolean var45 = false;
               ArrayList var47 = new ArrayList();

               for (INativeSignature var51 : var35.q()) {
                  if (var51.equals(var42)) {
                     var45 = true;
                  } else {
                     var30.put(var51.getTargetName(), var42.getTargetName());
                     var47.add(var51.getTargetName());
                     var0.getSignatures().remove(var51);
                     lm.q(var51.getTargetName(), q(var51), var42.getTargetName(), q(var42));
                  }
               }

               Assert.a(var45, "keeper signature not in the conflict");
               Assert.a(!Hk.containsKey(var42));
               Hk.put(var42, var47);
            } else if (!(var37 instanceof qV)) {
               if (!(var37 instanceof CU)) {
                  throw new RuntimeException("unknown solution");
               }

               for (INativeSignature var41 : var35.q()) {
                  var27.add(var41.getTargetName());
                  var0.getSignatures().remove(var41);
               }
            } else {
               ArrayList var10 = new ArrayList();

               for (INativeSignature var12 : var35.q()) {
                  var10.add(var12.getTargetName());
               }

               Collections.sort(var10);
               StringBuilder var40 = new StringBuilder();

               for (String var13 : var10) {
                  var40.append(Integer.toHexString(var13.hashCode()));
                  if (var40.length() > 50) {
                     break;
                  }
               }

               String var44 = var40.toString();
               boolean var46 = false;

               while (!var46) {
                  var46 = true;

                  for (INativeSignature var15 : var0.getSignatures()) {
                     if (var15.getTargetName().equals(var44)) {
                        var44 = var44 + "_D";
                        var46 = false;
                        break;
                     }
                  }
               }

               ArrayList var48 = new ArrayList();

               for (INativeFeature var17 : ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var35.q().iterator().next()).q()) {
                  var48.add(var17.deepCopy());
               }

               ArrayList var52 = null;

               for (INativeSignature var18 : var35.q()) {
                  if (var52 == null) {
                     var52 = new ArrayList(var18.getAttributes());
                  } else {
                     var52.retainAll(var18.getAttributes());
                  }
               }

               INativeSignature.ConfidenceLevel var54 = INativeSignature.ConfidenceLevel.HIGH;

               for (INativeSignature var19 : var35.q()) {
                  if (var19.getConfidenceLevel().compareTo(var54) < 0) {
                     var54 = var19.getConfidenceLevel();
                  }
               }

               com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV var56 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV(
                  var44, var48, var52, new NativeSignatureFlags(SignatureTargetType.ROUTINE, false), var54
               );
               ArrayList var57 = new ArrayList();

               for (INativeSignature var21 : var35.q()) {
                  if (var21.getFlags().hasMeaningfulTargetName()) {
                     var57.add(var21.getTargetName());
                     if (var21.getAlternateNames() != null && var21.getAlternateNames().size() != 0) {
                        var57.addAll(var21.getAlternateNames());
                     }
                  } else if (var21.getPossibleNames() != null && var21.getPossibleNames().size() != 0) {
                     var57.addAll(var21.getPossibleNames());
                  }
               }

               var56.RF(var57);
               var0.getSignatures().add(var56);

               for (INativeSignature var59 : var35.q()) {
                  var30.put(var59.getTargetName(), var44);
                  var0.getSignatures().remove(var59);
                  lm.q(var59.getTargetName(), q(var59), var56.getTargetName(), "mergeasunknown");
               }
            }
         }

         for (Entry var36 : var30.entrySet()) {
            q(var0, (String)var36.getKey(), (String)var36.getValue());
         }

         q(var0, var27, !var1.Me);
         return true;
      }
   }

   private static void q(INativeSignaturePackage var0, String var1, String var2) {
      if (!var1.equals(var2)) {
         label108:
         for (INativeSignature var4 : var0.getSignatures()) {
            bah var5 = null;
            bah var6 = null;
            bag var7 = null;
            bag var8 = null;

            for (INativeFeature var10 : ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var4).q()) {
               if (var10 instanceof bag) {
                  for (INativeFeature var12 : ((bag)var10).q()) {
                     if (!((bah)var12).Dw()) {
                        if (((bah)var12).q().equals(var1)) {
                           if (var7 != null) {
                              throw new RuntimeException("already a FD with the old name");
                           }

                           var5 = (bah)var12;
                           var7 = (bag)var10;
                        } else if (((bah)var12).q().equals(var2)) {
                           if (var8 != null) {
                              lm.q(var4.getTargetName(), q(var4), "Duplicated called routine names in different FD, cannot be rewritten (=> sig useles)");
                              continue label108;
                           }

                           var6 = (bah)var12;
                           var8 = (bag)var10;
                        }
                     }
                  }
               }

               if (var10 instanceof bah && !((bah)var10).Dw()) {
                  if (((bah)var10).q().equals(var1)) {
                     var5 = (bah)var10;
                  } else if (((bah)var10).q().equals(var2)) {
                     var6 = (bah)var10;
                  }
               }
            }

            if (var5 != null) {
               int var13 = var5.xK();
               if (var6 != null) {
                  if (var8 != null) {
                     if (var8 != var7) {
                        if (var7 == null) {
                           throw new RuntimeException("TBI: rewriting routine call, new name already exists *within* a FD");
                        }

                        int var15 = var6.xK();
                        int var18 = var5.xK();
                        bah var20 = new bah(var2, var5.RF(), var15 + var18);
                        var8.q().add(var20);
                        var7.q().add(var20);
                        continue;
                     }

                     Assert.a(var7.q().size() == 2);
                     Iterator var14 = var7.q().iterator();
                     int var17 = ((bah)var14.next()).xK();
                     int var19 = ((bah)var14.next()).xK();
                     Assert.a(var17 == var19);
                     var13 = var6.xK();
                     ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV)var4).q().remove(var8);
                     var7 = null;
                  } else {
                     Assert.a(var6.RF() == var5.RF());
                     var13 += var6.xK();
                     ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV)var4).q().remove(var6);
                  }
               }

               bah var16 = new bah(var2, var5.RF(), var13);
               if (var7 == null) {
                  ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV)var4).q().add(var16);
               } else {
                  if (var6 != null) {
                     throw new RuntimeException("TBI: replacing routine call, old name within a FD, new name already here *not* in FD");
                  }

                  var7.q().add(var16);
               }

               if (var7 == null) {
                  ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV)var4).q().remove(var5);
               } else {
                  var7.q().remove(var5);
               }
            }
         }
      }
   }

   private static boolean q(INativeSignaturePackage var0, Set var1, boolean var2) {
      if (var1.isEmpty()) {
         return false;
      } else {
         boolean var3 = false;
         ArrayList var4 = new ArrayList();
         HashSet var5 = new HashSet();

         for (INativeSignature var7 : var0.getSignatures()) {
            ArrayList var8 = new ArrayList();

            for (INativeFeature var10 : ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var7).q()) {
               boolean var11 = false;
               if (var10 instanceof bag) {
                  for (INativeFeature var13 : ((bag)var10).q()) {
                     if (var1.contains(((bah)var13).q())) {
                        var11 = true;
                        break;
                     }
                  }
               }

               if (var10 instanceof bah && var1.contains(((bah)var10).q())) {
                  var11 = true;
               }

               if (var11) {
                  var8.add(var10);
                  if (RF(RF(var7))) {
                     if (var2) {
                        String var19 = var7.getTargetName();
                        var4.add(var7);
                        var5.add(var19);
                     } else {
                        ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV)var7).q(INativeSignature.ConfidenceLevel.LOW);
                     }
                  }
               }
            }

            if (((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var7).q().removeAll(var8) && !var3) {
               var3 = true;
            }
         }

         if (JY != null) {
            for (INativeSignature var16 : var4) {
               JY.q(var16, null);
            }

            if (!JY.RF()) {
               JY.Dw();
               Object[] var10000 = new Object[]{JY.xK(), File.separator, "removals.txt"};
               System.exit(0);
            }

            for (INativeSignature var17 : var4) {
               PY.eo var18 = JY.q(var17);
               if (var18 == PY.eo.RF) {
                  var0.getSignatures().remove(var17);
               } else {
                  if (var18 != PY.eo.q) {
                     throw new RuntimeException("unknown removal status");
                  }

                  var5.remove(var17.getTargetName());
               }
            }
         } else {
            var0.getSignatures().removeAll(var4);
         }

         if (q(var0, var5, var2) && !var3) {
            var3 = true;
         }

         return var3;
      }
   }

   private static boolean RF(INativeSignaturePackage var0, oL var1) {
      Object[] var10000 = new Object[0];
      var10000 = new Object[0];
      var10000 = new Object[0];
      HashSet var2 = new HashSet();

      for (INativeSignature var4 : var0.getSignatures()) {
         HashSet var5 = new HashSet();

         for (INativeSignature var7 : var0.getSignatures()) {
            if (var4 != var7 && var4.match(var7) && !var4.matchExactly(var7)) {
               var5.add(var7);
            }
         }

         if (!var5.isEmpty()) {
            tw var13 = new tw(var4, var5, true);
            q(var13.RF(), var13);

            for (INativeSignature var8 : var13.q()) {
               q(var8, var13);
            }

            var2.add(var13);
            zz.q(var13, null);
         }
      }

      if (var2.size() == 0) {
         return false;
      } else {
         if (!zz.xK()) {
            zz.Uv();
            var10000 = new Object[]{zz.Dw(), File.separator, "conflicts.txt"};
            System.exit(0);
         }

         HashSet var10 = new HashSet();

         for (tw var12 : zz.nf()) {
            Nt var14 = zz.q(var12);
            if (var14 instanceof CU) {
               var10.add(var12.RF().getTargetName());
               var0.getSignatures().remove(var12.RF());

               for (INativeSignature var19 : var12.q()) {
                  var10.add(var19.getTargetName());
                  var0.getSignatures().remove(var19);
               }
            } else {
               if (!(var14 instanceof nI)) {
                  throw new RuntimeException("unknown solution");
               }

               boolean var16 = true;
               Iterator var18 = var12.q().iterator();

               while (true) {
                  if (var18.hasNext()) {
                     INativeSignature var9 = (INativeSignature)var18.next();
                     if (!var9.getTargetName().equals(var12.RF().getTargetName())) {
                        continue;
                     }

                     var16 = false;
                  }

                  if (var16) {
                     var10.add(var12.RF().getTargetName());
                  }

                  var0.getSignatures().remove(var12.RF());
                  break;
               }
            }
         }

         q(var0, var10, !var1.Me);
         return true;
      }
   }

   private static void xK(INativeSignaturePackage var0) {
      Object[] var10000 = new Object[0];
      var10000 = new Object[0];
      var10000 = new Object[0];
      ArrayList var1 = new ArrayList();

      for (INativeSignature var3 : var0.getSignatures()) {
         boolean var4 = false;

         for (Set var6 : var1) {
            INativeSignature var7 = (INativeSignature)var6.iterator().next();
            if (var3.matchExactly(var7)) {
               var6.add(var3);
               var4 = true;
               break;
            }
         }

         if (!var4) {
            HashSet var18 = new HashSet();
            var18.add(var3);
            var1.add(var18);
         }
      }

      ArrayList var13 = new ArrayList();

      for (Set var16 : var1) {
         if (var16.size() >= 2) {
            var13.add(var16);
         }
      }

      if (!var13.isEmpty()) {
         for (Set var17 : var13) {
            HashMap var19 = new HashMap();

            for (INativeSignature var22 : var17) {
               Set var8 = (Set)PV.get(var22);
               if (var8 == null || var8.isEmpty()) {
                  break;
               }

               ArrayList var9 = new ArrayList(var8);

               for (INativeSignature var11 : var17) {
                  if (var22 != var11) {
                     Set var12 = (Set)PV.get(var11);
                     if (var12 != null) {
                        var9.removeAll(var12);
                     }
                  }
               }

               if (var9.isEmpty()) {
                  break;
               }

               var19.put(var22, var9);
            }

            if (var19.size() == var17.size()) {
               for (INativeSignature var23 : var17) {
                  INativeFeature var24 = null;

                  for (INativeFeature var27 : (List)var19.get(var23)) {
                     if (var27 instanceof bah && ((bah)var27).Dw()) {
                        var24 = var27;
                        break;
                     }
                  }

                  if (var24 == null) {
                     var24 = (INativeFeature)((List)var19.get(var23)).get(0);
                  }

                  if (((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var23).q().contains(var24)) {
                     throw new RuntimeException("TBF already present in signature!");
                  }

                  Set var26 = (Set)PV.get(var23);
                  var26.remove(var24);
                  ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var23).q().add(var24);
               }
            }
         }
      }
   }

   private static void Dw(INativeSignaturePackage var0) {
      Object[] var10000 = new Object[0];
      var10000 = new Object[0];
      var10000 = new Object[0];
      boolean var1 = true;

      while (var1) {
         var1 = false;

         for (INativeSignature var3 : var0.getSignatures()) {
            for (INativeSignature var5 : var0.getSignatures()) {
               if (var3 != var5 && var3.match(var5) && !var3.matchExactly(var5)) {
                  Set var6 = (Set)PV.get(var3);
                  if (var6 != null) {
                     ArrayList var7 = new ArrayList();
                     var7.removeAll(((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var5).q());
                     Set var8 = (Set)PV.get(var5);
                     if (var8 != null) {
                        var7.removeAll((Collection)PV.get(var5));
                     }

                     if (!var7.isEmpty()) {
                        INativeFeature var9 = null;

                        for (INativeFeature var11 : var7) {
                           if (var11 instanceof bah && ((bah)var11).Dw()) {
                              var9 = var11;
                              break;
                           }
                        }

                        if (var9 == null) {
                           var9 = (INativeFeature)var7.get(0);
                        }

                        if (((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV)var3).q().contains(var9)) {
                           throw new RuntimeException("TBF already present in signature!");
                        }

                        ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV)var3).q().add(var9);
                        ((Set)PV.get(var3)).remove(var9);
                        var1 = true;
                     }
                  }
               }
            }
         }
      }
   }

   private static Bu Uv(INativeSignaturePackage var0) {
      Bu var1 = new Bu();

      for (INativeSignature var3 : var0.getSignatures()) {
         Bu.eo var4 = var1.q(var3.getTargetName());
         var4.q("green");

         for (INativeFeature var6 : ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var3).q()) {
            if (var6 instanceof bah) {
               Bu.eo var7 = var1.q(var6.getValue());
               if (var7.xK().equals("grey")) {
                  var7.q("orange");
               }

               var1.q(var4, var7);
            }

            if (var6 instanceof baj) {
               Bu.eo var8 = var1.q(var6.toString());
               var1.q(var4, var8);
            }
         }
      }

      return var1;
   }

   private static INativeSignaturePackage oW(INativeSignaturePackage var0) {
      com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Vj var1 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Vj(var0.getMetadata());

      for (INativeSignature var3 : var0.getSignatures()) {
         String var4 = var3.getTargetName();
         boolean var5 = false;

         for (INativeSignature var7 : var1.getSignatures()) {
            String var8 = var7.getTargetName();
            if (var4.equals(var8) && var3.matchExactly(var7)) {
               var5 = true;
               break;
            }
         }

         if (!var5) {
            var1.q(var3);
         } else {
            lm.q();
            lm.q(var3.getTargetName(), q(var3), "removed because of a duplicate");
            Me.remove(var3);
            PV.remove(var3);
         }
      }

      return var1;
   }

   private static int RF(INativeSignature var0) {
      int var1 = 0;

      for (INativeFeature var3 : ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var0).q()) {
         if (var3 instanceof bap) {
            var1 = ((bap)var3).q();
            break;
         }
      }

      return var1;
   }

   private static baj xK(INativeSignature var0) {
      baj var1 = null;

      for (INativeFeature var3 : ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.CU)var0).q()) {
         if (var3 instanceof baj) {
            var1 = (baj)var3;
            break;
         }
      }

      return var1;
   }

   private static boolean q(int var0) {
      return var0 <= 5;
   }

   private static boolean RF(int var0) {
      return var0 > 5 && var0 <= 30;
   }

   private static boolean xK(int var0) {
      return var0 > 30 && var0 <= 600;
   }

   private static boolean Dw(int var0) {
      return var0 > 600;
   }

   private static void xK() {
      KT = null;
   }

   private static void q(abg var0, oL var1) {
      if (KT == null) {
         KT = new IdentityHashMap();
      }

      IProcessor var2 = var0.getProcessor();
      ICompiler var3 = var0.getCodeAnalyzer().getDetectedCompiler();
      if (var2 instanceof cti && var3 instanceof add && var1.JY) {
         for (axp var6 : var0.getInternalMethods()) {
            if (var6.mI() == null) {
               String var7 = var6.getName(false);
               if (var7.startsWith("?")) {
                  String var8 = RF(var7);
                  if (var8 != null) {
                     KT.put(var6, var8);
                  }
               }
            }
         }
      }

      Set var23 = var1.RF();
      if (var23 != null) {
         for (Couple var25 : var23) {
            String var26 = (String)var25.getFirst();
            Integer var27 = (Integer)var25.getSecond();
            axp var9 = var0.RF(var26);
            if (var9 != null && var9.oW() != null && var9.oW().getCFG().getInstructionCount() == var27) {
               CFG var10 = var9.oW().getCFG();
               IInstruction var11 = (IInstruction)var10.getInstructions().get(var27 - 1);
               IFlowInformation var12 = var11.getBreakingFlow(var10.getLastAddress());
               if (var12.isBroken() && var12.getTargets().size() == 1) {
                  ICodePointer var13 = (ICodePointer)var12.getTargets().get(0);
                  axp var14 = var0.RF(var13.getAddress());
                  if (var14 != null) {
                     Set var15 = var0.getCodeModel().getReferenceManager().getReferencesTo(var14.oW().getMemoryAddress());
                     if (var15 != null) {
                        for (IReference var17 : var15) {
                           if (var17.getFrom().isInternalAddress()) {
                              long var18 = var17.getFrom().getInternalAddress();
                              List var20 = var0.getCodeModel().getContainedRoutineAddresses(var18);
                              if (var20 != null && var20.size() == 1) {
                                 axp var21 = var0.RF((Long)var20.get(0));
                                 String var22 = var21.mI();
                                 if (var22 == null) {
                                    var22 = var21.getName(false);
                                 }

                                 if (var0.RF(var14.oW().getMemoryAddress()) != null) {
                                    var0.undefineItem(var14.oW().getMemoryAddress());
                                 }

                                 var0.undefineItem(var21.oW().getMemoryAddress());
                                 var0.q(var21.oW().getMemoryAddress(), var22);
                                 var0.getCodeAnalyzer().enqueuePointerForAnalysis(new CodePointer(var21.oW().getMemoryAddress()));
                                 var0.getCodeAnalyzer().analyze(true, true);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private static String RF(String var0) {
      File var1 = new File("C:\\Tools\\undname.exe");
      if (!var1.exists()) {
         za.error("Cant find undname.exe");
         return null;
      } else {
         String var2 = null;

         try {
            Process var3 = Runtime.getRuntime().exec(Strings.ff("C:\\Tools\\undname.exe %s", var0));
            var3.waitFor();
            BufferedReader var4 = new BufferedReader(new InputStreamReader(var3.getInputStream(), Charset.defaultCharset()));

            String var5;
            while ((var5 = var4.readLine()) != null) {
               if (var5.startsWith("is :- \"")) {
                  var2 = var5.substring(7, var5.length() - 1);
                  break;
               }
            }

            if (var2 != null) {
               int var6 = var2.indexOf("__thiscall");
               if (var6 != -1) {
                  int var7 = var2.indexOf("(");
                  if (var2.indexOf("(", var7 + 1) != -1) {
                     return null;
                  }

                  int var8 = var2.indexOf(")");
                  if (var2.indexOf(")", var8 + 1) != -1) {
                     return null;
                  }

                  var2 = var2.substring(var6 + 11, var7);
               } else {
                  int var11 = var2.indexOf("__cdecl");
                  if (var11 != -1) {
                     int var12 = var2.indexOf("(");
                     if (var2.indexOf("(", var12 + 1) != -1) {
                        return null;
                     }

                     int var9 = var2.indexOf(")");
                     if (var2.indexOf(")", var9 + 1) != -1) {
                        return null;
                     }

                     var2 = var2.substring(var11 + 8, var12);
                  }
               }
            }
         } catch (Exception var10) {
            za.catching(var10);
         }

         return var2;
      }
   }

   private static Map q(String var0, String var1) {
      File var2 = new File(var0.concat(File.separator).concat(var1).concat(".rtn_to_sign"));
      if (!var2.exists()) {
         return null;
      } else {
         List var3;
         try {
            var3 = IO.readLines(var2);
         } catch (IOException var9) {
            za.error("error when reading routine list file");
            return null;
         }

         HashMap var4 = new HashMap();

         for (String var6 : var3) {
            String var8 = null;
            Long var7;
            if (var6.indexOf(44) != -1) {
               var7 = Long.decode(var6.substring(0, var6.indexOf(44)));
               var8 = var6.substring(var6.indexOf(44) + 1);
            } else {
               var7 = Long.decode(var6);
            }

            var4.put(var7, var8);
         }

         return var4;
      }
   }

   public static boolean q(String var0, String var1, INativeSignaturePackage var2, oL var3) throws JebException, IOException {
      com.pnfsoftware.jeb.corei.parsers.asm.nativesig.nI var4;
      if (var3.q.isIntel()) {
         if (var1.endsWith(".exe")) {
            var4 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.nI(ProcessorUtil.toWellKnownUnitType(var3.q), 1, 0, var3.CE());
         } else {
            var4 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.nI(ProcessorUtil.toWellKnownUnitType(var3.q), 4, 0, var3.CE());
         }
      } else if (var3.q.isMIPS()) {
         var4 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.nI(ProcessorUtil.toWellKnownUnitType(var3.q), 1, 2, var3.CE());
      } else {
         var4 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.nI(ProcessorUtil.toWellKnownUnitType(var3.q), 1, 0, var3.CE());
      }

      if (var3.qa != null) {
         var4.q(var3.qa.getPropertyId());
      }

      HeadlessClientContext var5 = com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Bu.q(var4);

      try {
         var5.getEnginesContext().getNativeSignatureDBManager().setActive(false);
         List var6 = com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Bu.q(var5, var0, var1);
         if (var6.size() != 1) {
            Object[] var67 = new Object[0];
            return false;
         } else if (((IUnit)var6.get(0)).getChildren().size() == 0) {
            Object[] var66 = new Object[0];
            return false;
         } else {
            boolean var7 = false;
            ArrayList var8 = new ArrayList();
            String var9 = ((IUnit)var6.get(0)).getFormatType();
            if (var9.equals("wincoff") || var9.equals("elf") || var9.equals("winpe")) {
               var8.add((IUnit)var6.get(0));
            } else if (var9.equals("ar")) {
               var7 = true;
               var8.addAll(((IUnit)var6.get(0)).getChildren());
            } else {
               if (!WellKnownUnitTypes.isNativeCode(var9)) {
                  Object[] var10000 = new Object[0];
                  return false;
               }

               var8.add((IUnit)var6.get(0));
            }

            label498:
            for (IUnit var11 : var8) {
               Object[] var62 = new Object[]{var11.getName()};
               if (var11.getChildren().size() != 0 || var11.process() && var11.getChildren().size() != 0) {
                  if (!(var11 instanceof abg var12)) {
                     List var13 = UnitUtil.findChildrenByType(var11, abg.class, false);
                     if (var13 == null || var13.size() != 1) {
                        var62 = new Object[0];
                        return false;
                     }

                     var12 = (abg)var13.get(0);
                  }

                  if (!var12.isProcessed()) {
                     try {
                        if (!var12.process()) {
                           var62 = new Object[0];
                           return false;
                        }
                     } catch (Exception var39) {
                        var62 = new Object[0];
                        return false;
                     }
                  }

                  q(var12, var3);
                  aae var44 = (aae)var12.getCodeAnalyzer();
                  String var47;
                  if (var7) {
                     var47 = Paths.get(var11.getName()).getFileName().toString();
                  } else {
                     var47 = var1;
                  }

                  baq var15 = (baq)com.pnfsoftware.jeb.corei.parsers.asm.nativesig.iZ.q(NativeFeatureSignerID.ROUTINE_SIZE, var44);
                  bak var16 = (bak)com.pnfsoftware.jeb.corei.parsers.asm.nativesig.iZ.q(NativeFeatureSignerID.ROUTINE_CODE_HASH, var44);
                  bai var17 = (bai)com.pnfsoftware.jeb.corei.parsers.asm.nativesig.iZ.q(NativeFeatureSignerID.CALLED_ROUTINE_NAME, var44);
                  var17.q(var3.io);
                  bao var18 = (bao)com.pnfsoftware.jeb.corei.parsers.asm.nativesig.iZ.q(NativeFeatureSignerID.ROUTINE_1B_CTE, var44);
                  azw var19 = new azw();
                  if (var3.qa instanceof add) {
                     var19.q(oW);
                  } else if (var3.qa instanceof acw) {
                     var19.q(new azh());
                  }

                  bad var20 = new bad();
                  Map var21 = q(var0, var1);
                  if (var21 != null && var21.size() != 0) {
                     for (Entry var23 : var21.entrySet()) {
                        axp var24 = var12.RF((Long)var23.getKey());
                        if (var24 == null) {
                           long var25 = (Long)var23.getKey();
                           INativeContinuousItem var27 = var44.za().getItemAt(var25);
                           if (var27 instanceof axn) {
                              var44.za().q(var27, true);
                           }

                           var44.enqueuePointerForAnalysis(new CodePointer(var25), 1);
                           var44.analyze();
                           var24 = var12.RF((Long)var23.getKey());
                           if (var24 == null) {
                              throw new RuntimeException("Error with routine list: no routine at the provided address");
                           }
                        }

                        if (var23.getValue() != null) {
                           var24.oW().setName((String)var23.getValue());
                        }
                     }
                  }

                  Iterator var48 = var12.getInternalMethods().iterator();

                  while (true) {
                     axp var50;
                     String var52;
                     while (true) {
                        if (!var48.hasNext()) {
                           if (var21 == null || var21.size() == 0) {
                              continue label498;
                           }

                           var48 = var21.entrySet().iterator();

                           while (true) {
                              if (!var48.hasNext()) {
                                 continue label498;
                              }

                              Entry var51 = (Entry)var48.next();
                              axp var53 = var12.RF((Long)var51.getKey());
                              if (var53 == null) {
                                 throw new RuntimeException("problem with routine list creation");
                              }

                              if (!var53.getName(true).equals(var51.getValue())) {
                                 q(var2, var53.getName(true), (String)var51.getValue());
                              }
                           }
                        }

                        var50 = (axp)var48.next();
                        var52 = null;
                        if (var21 == null || var21.size() == 0) {
                           break;
                        }

                        if (var21.containsKey(var50.oW().getMemoryAddress())) {
                           var52 = (String)var21.get(var50.oW().getMemoryAddress());
                           break;
                        }
                     }

                     if (var52 == null && ((v)var12.getCodeModel().getLabelManager()).q(var50.getName(true))) {
                        lm.q(var47);
                     } else if (var52 == null && var50.getName(true).startsWith("_ptr_")) {
                        lm.q(var47);
                     } else {
                        if (var52 == null) {
                           var52 = var50.mI();
                        }

                        if (var52 == null) {
                           var52 = var50.getName(false);
                           if (((v)var12.getCodeModel().getLabelManager()).q(var52) && !((v)var12.getCodeModel().getLabelManager()).q(var50.getName(true))) {
                              var52 = var50.getName(true);
                           }
                        }

                        if (var3.RF(var47, var52)) {
                           lm.q(var52, var47, vb.eo.q);
                        } else if (var50.wF() == null && !var50.If()) {
                           com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV var54;
                           if (var3.Ef() instanceof com.pnfsoftware.jeb.corei.parsers.asm.nativesig.eo) {
                              NativeSignatureGenerator var26 = NativeSignatureGenerator.getInstance(var3.Ef());
                              var26.setDefaultStrategy(var3.Ef());
                              var54 = (com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV)var26.generateSignature(var44, var50, null, null);
                              int var56 = var50.oW().getCFG().getInstructionCount();
                              vb.eo var28 = vb.eo.Dw;
                              if (q(var56)) {
                                 var28 = vb.eo.RF;
                              } else if (RF(var56)) {
                                 var28 = vb.eo.xK;
                              } else if (xK(var56)) {
                                 var28 = vb.eo.Dw;
                              } else if (Dw(var56)) {
                                 var28 = vb.eo.Uv;
                              }

                              lm.q(var52, var47, var28);
                              lm.q(var52, var47, var54);
                           } else {
                              if (!(var3.Ef() instanceof com.pnfsoftware.jeb.corei.parsers.asm.nativesig.bK)) {
                                 throw new RuntimeException(Strings.ff("TBI: config %s", var3.getClass().getCanonicalName()));
                              }

                              ArrayList var55 = new ArrayList();
                              HashSet var57 = new HashSet();
                              ArrayList var58 = new ArrayList();
                              INativeSignature.ConfidenceLevel var29 = INativeSignature.ConfidenceLevel.MEDIUM;
                              boolean var30 = false;
                              int var31 = var50.oW().getCFG().getInstructionCount();
                              if (q(var31) && !var3.xK().contains(var52)) {
                                 LK.add(var52);
                                 lm.q(var52, var47, vb.eo.RF);
                                 continue;
                              }

                              if (RF(var31) || q(var31) && var3.xK().contains(var52)) {
                                 for (CallGraphVertex var34 : var44.za().getCallGraphManager().getGlobalCallGraph().getCallees(var50, true)) {
                                    if (var34.isInternal()) {
                                       INativeContinuousItem var35 = var44.za().getItemAt(var34.getInternalAddress().getAddress());
                                       if (var35 != null && !var17.RF(var35.getName(true))) {
                                          var30 = true;
                                          break;
                                       }
                                    } else if (!var34.isResolved() && !var17.RF(var44.za().getItemAt(var34.getDereferencedAddress()).getName(true))) {
                                       var30 = true;
                                       break;
                                    }
                                 }

                                 if (var30) {
                                    lm.q(var52, var47, "unnamed call");
                                    var29 = INativeSignature.ConfidenceLevel.LOW;
                                 }

                                 var15.q(var50);
                                 var55.addAll(var15.q());
                                 var15.RF();
                                 var16.q(var50);
                                 var55.addAll(var16.q());
                                 var16.RF();
                                 var17.q(var50);
                                 var55.addAll(var17.q());
                                 var17.RF();
                                 var18.q(var50);
                                 var57.addAll(var18.q());
                                 var18.RF();
                                 lm.q(var52, var47, vb.eo.xK);
                              } else if (xK(var31)) {
                                 var15.q(var50);
                                 var55.addAll(var15.q());
                                 var15.RF();
                                 var16.q(var50);
                                 var55.addAll(var16.q());
                                 var16.RF();
                                 var17.q(var50);
                                 var57.addAll(var17.q());
                                 var17.RF();
                                 lm.q(var52, var47, vb.eo.Dw);
                              } else if (Dw(var31)) {
                                 var15.q(var50);
                                 var55.addAll(var15.q());
                                 var15.RF();
                                 var16.q(var50);
                                 var55.addAll(var16.q());
                                 var16.RF();
                                 lm.q(var52, var47, vb.eo.Uv);
                              }

                              if (var7) {
                                 var58.add(new azr(var47, var1));
                              } else {
                                 var58.add(new azr(var1));
                              }

                              azq var59 = new azq(var3.gP);
                              var58.add(var59);
                              String var60 = (String)KT.get(var50);
                              if (var60 != null) {
                                 azt var61 = new azt(var60);
                                 var58.add(var61);
                              }

                              var19.q(var50);
                              var58.addAll(var19.q());
                              var19.RF();
                              var20.q(var50);
                              var58.addAll(var20.q());
                              var20.RF();
                              var54 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qV(
                                 var52, var55, var58, new NativeSignatureFlags(SignatureTargetType.ROUTINE), var29
                              );
                              PV.put(var54, var57);
                              lm.q(var52, var47, var54);
                              lm.q(var52, var47, var57);
                              if (var30) {
                                 Me.add(var54);
                              }
                           }

                           if (var54 == null) {
                              throw new RuntimeException("error: null signature");
                           }

                           ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Vj)var2).q(var54);
                        }
                     }
                  }
               }
            }

            xK();
            return true;
         }
      } finally {
         com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Bu.q(var5, var4);
      }
   }

   public static bK q() {
      return lm;
   }

   static {
      oW.add("_wmain");
      oW.add("_main");
      oW.add("_wWinMain@16");
      oW.add("_WinMain@16");
      oW.add("_DllMain@12");
      oW.add("wmain");
      oW.add("main");
      oW.add("wWinMain");
      oW.add("WinMain");
      oW.add("DllMain");
   }

   public static enum eo {
      q,
      RF,
      xK,
      Dw,
      Uv;
   }
}
