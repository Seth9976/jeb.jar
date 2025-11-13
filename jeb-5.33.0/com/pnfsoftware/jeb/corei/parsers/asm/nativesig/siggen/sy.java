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
import com.pnfsoftware.jeb.corei.parsers.x86.Or;
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
import com.pnfsoftware.jebglobal.C;
import com.pnfsoftware.jebglobal.a;
import com.pnfsoftware.jebglobal.abe;
import com.pnfsoftware.jebglobal.abl;
import com.pnfsoftware.jebglobal.aus;
import com.pnfsoftware.jebglobal.auu;
import com.pnfsoftware.jebglobal.avp;
import com.pnfsoftware.jebglobal.avq;
import com.pnfsoftware.jebglobal.awk;
import com.pnfsoftware.jebglobal.awt;
import com.pnfsoftware.jebglobal.awu;
import com.pnfsoftware.jebglobal.aww;
import com.pnfsoftware.jebglobal.awy;
import com.pnfsoftware.jebglobal.awz;
import com.pnfsoftware.jebglobal.axg;
import com.pnfsoftware.jebglobal.axj;
import com.pnfsoftware.jebglobal.axk;
import com.pnfsoftware.jebglobal.axl;
import com.pnfsoftware.jebglobal.axn;
import com.pnfsoftware.jebglobal.axq;
import com.pnfsoftware.jebglobal.axr;
import com.pnfsoftware.jebglobal.axs;
import com.pnfsoftware.jebglobal.ckh;
import com.pnfsoftware.jebglobal.ph;
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

public class sy {
   private static final ILogger A = GlobalLog.getLogger(sy.class);
   private static oP kS = new oP();
   private static com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen.Av wS;
   private static RC UT;
   static final Set pC = new HashSet();
   private static final Set E = new HashSet();
   private static final Set sY = new HashSet();
   private static final Map ys = new HashMap();
   private static final Map ld = new HashMap();
   private static final Map gp = new HashMap();
   private static final Set oT = new HashSet();
   private static final Map fI = new IdentityHashMap();
   private static ICompiler WR;
   private static List NS = new ArrayList();
   private static Map vP = null;

   public static INativeSignaturePackage pC(String var0, HE var1, boolean var2, boolean var3, boolean var4) throws JebException, IOException {
      String var5 = qt.pC(var1);
      if (!var5.isEmpty()) {
         throw new JebRuntimeException("Invalid config:" + var5);
      } else {
         String var6 = "input";
         if (var1.rl() != null) {
            var6 = var1.rl();
         }

         String var7 = var0.concat(File.separator).concat(var6);
         if (!new File(var7).exists()) {
            Object[] var10000 = new Object[]{var7};
            System.exit(0);
         }

         String var8 = var1.A;
         ArrayList var9 = new ArrayList();
         if (var8 != null && !var8.equals("")) {
            throw new RuntimeException("TBI: target file config option");
         } else {
            for (File var11 : IO.listFiles(var7)) {
               if (!var1.kS.isEmpty()) {
                  for (String var13 : var1.kS) {
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
            NativeSignaturePackageMetadata var23 = NativeSignaturePackageMetadata.create(var1.pC, var1.UT, var1.UO, var1.E, var1.sY, var1.ys, var1.ED, var1.eP);
            INativeSignaturePackage var24 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qt(var23);
            WR = var1.ED;

            for (File var30 : var9) {
               int var14 = var24.count();
               pC(var30.getParent(), var30.getName(), var24, var1);
               var73 = new Object[]{var30.getName(), var24.count() - var14};
            }

            wS = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen.Av(
               var0,
               var2 ? com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen.Av.wS : com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen.Av.pC,
               var2 ? com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen.Av.kS : com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen.Av.pC
            );
            wS.E();
            if (var1.NS) {
               UT = new RC(var0, var2 ? RC.Av.A : RC.Av.pC);
               UT.wS();
            }

            pC();
            var24 = UT(var24);
            pC(var24, sY, !var1.ah);

            for (Set var31 : fI.values()) {
               ArrayList var37 = new ArrayList();

               for (INativeFeature var16 : var31) {
                  boolean var17 = false;
                  if (var16 instanceof axj) {
                     for (INativeFeature var19 : ((axj)var16).pC()) {
                        if (sY.contains(((axk)var19).pC())) {
                           var17 = true;
                           break;
                        }
                     }
                  }

                  if (var16 instanceof axk && sY.contains(((axk)var16).pC())) {
                     var17 = true;
                  }

                  if (var17) {
                     var37.add(var16);
                  }
               }

               var31.removeAll(var37);
            }

            if (!var1.ah) {
               HashSet var28 = new HashSet();
               if (var1.NS) {
                  for (INativeSignature var38 : oT) {
                     UT.pC(var38, null);
                  }

                  if (!UT.pC()) {
                     UT.kS();
                     var73 = new Object[]{UT.A(), File.separator, "removals.txt"};
                     System.exit(0);
                  }

                  for (INativeSignature var39 : oT) {
                     RC.Av var44 = UT.pC(var39);
                     if (var44 == RC.Av.A) {
                        var24.getSignatures().remove(var39);
                        var28.add(var39.getTargetName());
                     } else if (var44 != RC.Av.pC) {
                        throw new RuntimeException("unknown removal status");
                     }
                  }

                  pC(var24, var28, true);
               } else {
                  for (INativeSignature var40 : oT) {
                     var24.getSignatures().remove(var40);
                     var28.add(var40.getTargetName());
                  }

                  pC(var24, var28, true);
               }
            }

            A(var24);
            int var29 = 0;

            do {
               var73 = new Object[]{var29};
               var29++;
               kS(var24);
               wS(var24);
               pC(var24);
               A(var24);
            } while (A(var24, var1) || pC(var24, var1));

            if (wS.pC()) {
               for (INativeSignature var41 : var24.getSignatures()) {
                  List var45 = (List)gp.get(var41);
                  if (var45 != null) {
                     ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD)var41).pC(var45);
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

               com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qt var43 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qt(var24.getMetadata());

               for (INativeSignature var51 : var24.getSignatures()) {
                  ArrayList var55 = new ArrayList();
                  HashSet var59 = new HashSet();

                  for (INativeFeature var20 : ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Sv)var51).A()) {
                     if (var20 instanceof axk) {
                        var59.add(((axk)var20).pC());
                     }
                  }

                  for (INativeAttribute var69 : var51.getAttributes()) {
                     if (var69 instanceof awy) {
                        String var21 = (String)((awy)var69).pC().getSecond();
                        if (var36.contains(var21) || var59.contains(var21)) {
                           continue;
                        }
                     }

                     var55.add(var69);
                  }

                  com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD var65 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD(
                     var51.getTargetName(),
                     ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Sv)var51).pC(),
                     var55,
                     var51.getFlags(),
                     var51.getConfidenceLevel()
                  );
                  var65.pC(var51.getAlternateNames());
                  var65.A(var51.getPossibleNames());
                  var43.pC(var65);
               }

               var24 = var43;

               for (INativeSignature var52 : var43.getSignatures()) {
                  String var56 = pC(var52);
                  String var60 = var52.getTargetName();
                  kS.A(var60, var56 != null ? var56 : "unknown", var52);
               }

               if (var4) {
                  kS.pC(var43, var0);
                  kS.A(var0);
                  UT.kS();
                  var73 = new Object[]{UT.A(), File.separator, "removals.txt"};
                  wS.UT();
                  var73 = new Object[]{wS.wS(), File.separator, "conflicts.txt"};
               }

               if (!var1.vP) {
                  com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qt var49 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qt(var43.getMetadata());

                  for (INativeSignature var57 : var43.getSignatures()) {
                     ArrayList var61 = new ArrayList();

                     for (INativeAttribute var70 : var57.getAttributes()) {
                        if (!(var70 instanceof awu) && !(var70 instanceof awt)) {
                           var61.add(var70);
                        }
                     }

                     com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD var67 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD(
                        var57.getTargetName(),
                        ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Sv)var57).pC(),
                        var61,
                        var57.getFlags(),
                        var57.getConfidenceLevel()
                     );
                     var67.pC(var57.getAlternateNames());
                     var67.A(var57.getPossibleNames());
                     var49.pC(var67);
                  }

                  var24 = var49;
               }

               if (var3) {
                  if (var24.count() > 0) {
                     ckh var50 = ckh.pC();
                     SerializationManager var54 = new SerializationManager(var50);
                     DirectByteArrayOutputStream var58 = new DirectByteArrayOutputStream();
                     Serializer var62 = var54.getSerializer(var58);

                     try {
                        var62.serialize(var23);
                        var62.serialize(var24);
                        var62.close();
                     } catch (SerializationException var22) {
                        A.catching(var22);
                     }

                     File var68 = new File(var0.concat(File.separator).concat("output"));
                     if (!var68.exists()) {
                        IO.createDirectory(var68);
                     }

                     File var71 = new File(var0.concat(File.separator).concat("output"), var1.wS + ".siglib");
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

   private static String pC(INativeSignature var0) {
      for (INativeAttribute var2 : var0.getAttributes()) {
         if (var2 instanceof awu) {
            return ((awu)var2).pC();
         }
      }

      return null;
   }

   private static void pC() {
      if (WR.isWindowsCompatible()) {
         NS.add(new avp());
         NS.add(new avq());
      } else if (WR.isLinuxCompatible()) {
         NS.add(new awk());
      } else {
         NS.add(new avp());
         NS.add(new avq());
         NS.add(new awk());
      }
   }

   private static String pC(String var0) {
      IUnmangledData var1 = null;

      for (IManglingEngine var3 : NS) {
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

      return WR.isWindowsCompatible() ? A(var0) : null;
   }

   private static void pC(INativeSignaturePackage var0) {
      ys.clear();
      ld.clear();

      for (INativeSignature var2 : var0.getSignatures()) {
         int var3 = A(var2);

         for (INativeFeature var5 : ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Sv)var2).A()) {
            if (var5 instanceof axk) {
               pC(var5, var3);
            }
         }
      }
   }

   private static void pC(INativeFeature var0, int var1) {
      String var2 = ((axk)var0).pC();
      if (!ys.containsKey(var2)) {
         ys.put(var2, 1);
         if (A(var1)) {
            ld.put(var2, 1);
         }
      } else {
         int var3 = (Integer)ys.get(var2);
         ys.put(var2, var3 + 1);
         if (A(var1)) {
            ld.put(var2, var3 + 1);
         }
      }
   }

   private static void A(INativeSignaturePackage var0) {
      for (INativeSignature var2 : var0.getSignatures()) {
         for (INativeSignature var4 : var0.getSignatures()) {
            if (var2 != var4) {
               String var5 = var2.getTargetName();
               if (var5.equals(var4.getTargetName())) {
                  E.add(var5);
               }
            }
         }
      }
   }

   private static void pC(INativeSignature var0, DH var1) {
      String var2 = var0.getTargetName();
      String var3 = pC(var2);
      if (var3 != null) {
         var1.pC(var0, var3);
      }

      var1.pC(
         var0,
         Strings.ff(
            "[%d CalledRoutineName - %d in small routines - unique name: %b]",
            ys.containsKey(var2) ? (Integer)ys.get(var2) : 0,
            ld.containsKey(var2) ? (Integer)ld.get(var2) : 0,
            !E.contains(var2)
         )
      );
   }

   private static boolean pC(INativeSignaturePackage var0, HE var1) {
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
            bO var31 = new bO(var29, true);

            for (INativeSignature var9 : var31.pC()) {
               pC(var9, var31);
            }

            var24.add(var31);
            wS.pC(var31, null);
         }

         if (!wS.A()) {
            wS.UT();
            var10000 = new Object[]{wS.wS(), File.separator, "conflicts.txt"};
            System.exit(0);
         }

         HashSet var27 = new HashSet();
         HashMap var30 = new HashMap();

         for (bO var35 : var24) {
            cq var37 = wS.pC(var35);
            if (var37 instanceof yt) {
               int var39 = ((yt)var37).pC();
               INativeSignature var42 = wS.pC(var39);
               boolean var45 = false;
               ArrayList var47 = new ArrayList();

               for (INativeSignature var51 : var35.pC()) {
                  if (var51.equals(var42)) {
                     var45 = true;
                  } else {
                     var30.put(var51.getTargetName(), var42.getTargetName());
                     var47.add(var51.getTargetName());
                     var0.getSignatures().remove(var51);
                     kS.pC(var51.getTargetName(), pC(var51), var42.getTargetName(), pC(var42));
                  }
               }

               Assert.a(var45, "keeper signature not in the conflict");
               Assert.a(!gp.containsKey(var42));
               gp.put(var42, var47);
            } else if (!(var37 instanceof KD)) {
               if (!(var37 instanceof Sv)) {
                  throw new RuntimeException("unknown solution");
               }

               for (INativeSignature var41 : var35.pC()) {
                  var27.add(var41.getTargetName());
                  var0.getSignatures().remove(var41);
               }
            } else {
               ArrayList var10 = new ArrayList();

               for (INativeSignature var12 : var35.pC()) {
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

               for (INativeFeature var17 : ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Sv)var35.pC().iterator().next()).pC()) {
                  var48.add(var17.deepCopy());
               }

               ArrayList var52 = null;

               for (INativeSignature var18 : var35.pC()) {
                  if (var52 == null) {
                     var52 = new ArrayList(var18.getAttributes());
                  } else {
                     var52.retainAll(var18.getAttributes());
                  }
               }

               INativeSignature.ConfidenceLevel var54 = INativeSignature.ConfidenceLevel.HIGH;

               for (INativeSignature var19 : var35.pC()) {
                  if (var19.getConfidenceLevel().compareTo(var54) < 0) {
                     var54 = var19.getConfidenceLevel();
                  }
               }

               com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD var56 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD(
                  var44, var48, var52, new NativeSignatureFlags(SignatureTargetType.ROUTINE, false), var54
               );
               ArrayList var57 = new ArrayList();

               for (INativeSignature var21 : var35.pC()) {
                  if (var21.getFlags().hasMeaningfulTargetName()) {
                     var57.add(var21.getTargetName());
                     if (var21.getAlternateNames() != null && var21.getAlternateNames().size() != 0) {
                        var57.addAll(var21.getAlternateNames());
                     }
                  } else if (var21.getPossibleNames() != null && var21.getPossibleNames().size() != 0) {
                     var57.addAll(var21.getPossibleNames());
                  }
               }

               var56.A(var57);
               var0.getSignatures().add(var56);

               for (INativeSignature var59 : var35.pC()) {
                  var30.put(var59.getTargetName(), var44);
                  var0.getSignatures().remove(var59);
                  kS.pC(var59.getTargetName(), pC(var59), var56.getTargetName(), "mergeasunknown");
               }
            }
         }

         for (Entry var36 : var30.entrySet()) {
            pC(var0, (String)var36.getKey(), (String)var36.getValue());
         }

         pC(var0, var27, !var1.ah);
         return true;
      }
   }

   private static void pC(INativeSignaturePackage var0, String var1, String var2) {
      if (!var1.equals(var2)) {
         label108:
         for (INativeSignature var4 : var0.getSignatures()) {
            axk var5 = null;
            axk var6 = null;
            axj var7 = null;
            axj var8 = null;

            for (INativeFeature var10 : ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Sv)var4).pC()) {
               if (var10 instanceof axj) {
                  for (INativeFeature var12 : ((axj)var10).pC()) {
                     if (!((axk)var12).wS()) {
                        if (((axk)var12).pC().equals(var1)) {
                           if (var7 != null) {
                              throw new RuntimeException("already a FD with the old name");
                           }

                           var5 = (axk)var12;
                           var7 = (axj)var10;
                        } else if (((axk)var12).pC().equals(var2)) {
                           if (var8 != null) {
                              kS.pC(var4.getTargetName(), pC(var4), "Duplicated called routine names in different FD, cannot be rewritten (=> sig useles)");
                              continue label108;
                           }

                           var6 = (axk)var12;
                           var8 = (axj)var10;
                        }
                     }
                  }
               }

               if (var10 instanceof axk && !((axk)var10).wS()) {
                  if (((axk)var10).pC().equals(var1)) {
                     var5 = (axk)var10;
                  } else if (((axk)var10).pC().equals(var2)) {
                     var6 = (axk)var10;
                  }
               }
            }

            if (var5 != null) {
               int var13 = var5.kS();
               if (var6 != null) {
                  if (var8 != null) {
                     if (var8 != var7) {
                        if (var7 == null) {
                           throw new RuntimeException("TBI: rewriting routine call, new name already exists *within* a FD");
                        }

                        int var15 = var6.kS();
                        int var18 = var5.kS();
                        axk var20 = new axk(var2, var5.A(), var15 + var18);
                        var8.pC().add(var20);
                        var7.pC().add(var20);
                        continue;
                     }

                     Assert.a(var7.pC().size() == 2);
                     Iterator var14 = var7.pC().iterator();
                     int var17 = ((axk)var14.next()).kS();
                     int var19 = ((axk)var14.next()).kS();
                     Assert.a(var17 == var19);
                     var13 = var6.kS();
                     ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD)var4).pC().remove(var8);
                     var7 = null;
                  } else {
                     Assert.a(var6.A() == var5.A());
                     var13 += var6.kS();
                     ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD)var4).pC().remove(var6);
                  }
               }

               axk var16 = new axk(var2, var5.A(), var13);
               if (var7 == null) {
                  ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD)var4).pC().add(var16);
               } else {
                  if (var6 != null) {
                     throw new RuntimeException("TBI: replacing routine call, old name within a FD, new name already here *not* in FD");
                  }

                  var7.pC().add(var16);
               }

               if (var7 == null) {
                  ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD)var4).pC().remove(var5);
               } else {
                  var7.pC().remove(var5);
               }
            }
         }
      }
   }

   private static boolean pC(INativeSignaturePackage var0, Set var1, boolean var2) {
      if (var1.isEmpty()) {
         return false;
      } else {
         boolean var3 = false;
         ArrayList var4 = new ArrayList();
         HashSet var5 = new HashSet();

         for (INativeSignature var7 : var0.getSignatures()) {
            ArrayList var8 = new ArrayList();

            for (INativeFeature var10 : ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Sv)var7).pC()) {
               boolean var11 = false;
               if (var10 instanceof axj) {
                  for (INativeFeature var13 : ((axj)var10).pC()) {
                     if (var1.contains(((axk)var13).pC())) {
                        var11 = true;
                        break;
                     }
                  }
               }

               if (var10 instanceof axk && var1.contains(((axk)var10).pC())) {
                  var11 = true;
               }

               if (var11) {
                  var8.add(var10);
                  if (A(A(var7))) {
                     if (var2) {
                        String var19 = var7.getTargetName();
                        var4.add(var7);
                        var5.add(var19);
                     } else {
                        ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD)var7).pC(INativeSignature.ConfidenceLevel.LOW);
                     }
                  }
               }
            }

            if (((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Sv)var7).pC().removeAll(var8) && !var3) {
               var3 = true;
            }
         }

         if (UT != null) {
            for (INativeSignature var16 : var4) {
               UT.pC(var16, null);
            }

            if (!UT.pC()) {
               UT.kS();
               Object[] var10000 = new Object[]{UT.A(), File.separator, "removals.txt"};
               System.exit(0);
            }

            for (INativeSignature var17 : var4) {
               RC.Av var18 = UT.pC(var17);
               if (var18 == RC.Av.A) {
                  var0.getSignatures().remove(var17);
               } else {
                  if (var18 != RC.Av.pC) {
                     throw new RuntimeException("unknown removal status");
                  }

                  var5.remove(var17.getTargetName());
               }
            }
         } else {
            var0.getSignatures().removeAll(var4);
         }

         if (pC(var0, var5, var2) && !var3) {
            var3 = true;
         }

         return var3;
      }
   }

   private static boolean A(INativeSignaturePackage var0, HE var1) {
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
            rQ var13 = new rQ(var4, var5, true);
            pC(var13.A(), var13);

            for (INativeSignature var8 : var13.pC()) {
               pC(var8, var13);
            }

            var2.add(var13);
            wS.pC(var13, null);
         }
      }

      if (var2.size() == 0) {
         return false;
      } else {
         if (!wS.kS()) {
            wS.UT();
            var10000 = new Object[]{wS.wS(), File.separator, "conflicts.txt"};
            System.exit(0);
         }

         HashSet var10 = new HashSet();

         for (rQ var12 : wS.sY()) {
            cq var14 = wS.pC(var12);
            if (var14 instanceof Sv) {
               var10.add(var12.A().getTargetName());
               var0.getSignatures().remove(var12.A());

               for (INativeSignature var19 : var12.pC()) {
                  var10.add(var19.getTargetName());
                  var0.getSignatures().remove(var19);
               }
            } else {
               if (!(var14 instanceof K)) {
                  throw new RuntimeException("unknown solution");
               }

               boolean var16 = true;
               Iterator var18 = var12.pC().iterator();

               while (true) {
                  if (var18.hasNext()) {
                     INativeSignature var9 = (INativeSignature)var18.next();
                     if (!var9.getTargetName().equals(var12.A().getTargetName())) {
                        continue;
                     }

                     var16 = false;
                  }

                  if (var16) {
                     var10.add(var12.A().getTargetName());
                  }

                  var0.getSignatures().remove(var12.A());
                  break;
               }
            }
         }

         pC(var0, var10, !var1.ah);
         return true;
      }
   }

   private static void kS(INativeSignaturePackage var0) {
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
               Set var8 = (Set)fI.get(var22);
               if (var8 == null || var8.isEmpty()) {
                  break;
               }

               ArrayList var9 = new ArrayList(var8);

               for (INativeSignature var11 : var17) {
                  if (var22 != var11) {
                     Set var12 = (Set)fI.get(var11);
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
                     if (var27 instanceof axk && ((axk)var27).wS()) {
                        var24 = var27;
                        break;
                     }
                  }

                  if (var24 == null) {
                     var24 = (INativeFeature)((List)var19.get(var23)).get(0);
                  }

                  if (((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Sv)var23).pC().contains(var24)) {
                     throw new RuntimeException("TBF already present in signature!");
                  }

                  Set var26 = (Set)fI.get(var23);
                  var26.remove(var24);
                  ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Sv)var23).pC().add(var24);
               }
            }
         }
      }
   }

   private static void wS(INativeSignaturePackage var0) {
      Object[] var10000 = new Object[0];
      var10000 = new Object[0];
      var10000 = new Object[0];
      boolean var1 = true;

      while (var1) {
         var1 = false;

         for (INativeSignature var3 : var0.getSignatures()) {
            for (INativeSignature var5 : var0.getSignatures()) {
               if (var3 != var5 && var3.match(var5) && !var3.matchExactly(var5)) {
                  Set var6 = (Set)fI.get(var3);
                  if (var6 != null) {
                     ArrayList var7 = new ArrayList();
                     var7.removeAll(((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Sv)var5).pC());
                     Set var8 = (Set)fI.get(var5);
                     if (var8 != null) {
                        var7.removeAll((Collection)fI.get(var5));
                     }

                     if (!var7.isEmpty()) {
                        INativeFeature var9 = null;

                        for (INativeFeature var11 : var7) {
                           if (var11 instanceof axk && ((axk)var11).wS()) {
                              var9 = var11;
                              break;
                           }
                        }

                        if (var9 == null) {
                           var9 = (INativeFeature)var7.get(0);
                        }

                        if (((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD)var3).pC().contains(var9)) {
                           throw new RuntimeException("TBF already present in signature!");
                        }

                        ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD)var3).pC().add(var9);
                        ((Set)fI.get(var3)).remove(var9);
                        var1 = true;
                     }
                  }
               }
            }
         }
      }
   }

   private static INativeSignaturePackage UT(INativeSignaturePackage var0) {
      com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qt var1 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qt(var0.getMetadata());

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
            var1.pC(var3);
         } else {
            kS.pC();
            kS.pC(var3.getTargetName(), pC(var3), "removed because of a duplicate");
            oT.remove(var3);
            fI.remove(var3);
         }
      }

      return var1;
   }

   private static int A(INativeSignature var0) {
      int var1 = 0;

      for (INativeFeature var3 : ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Sv)var0).pC()) {
         if (var3 instanceof axr) {
            var1 = ((axr)var3).pC();
            break;
         }
      }

      return var1;
   }

   private static boolean pC(int var0) {
      return var0 <= 5;
   }

   private static boolean A(int var0) {
      return var0 > 5 && var0 <= 30;
   }

   private static boolean kS(int var0) {
      return var0 > 30 && var0 <= 600;
   }

   private static boolean wS(int var0) {
      return var0 > 600;
   }

   private static void A() {
      vP = null;
   }

   private static void pC(C var0, HE var1) {
      if (vP == null) {
         vP = new IdentityHashMap();
      }

      IProcessor var2 = var0.getProcessor();
      ICompiler var3 = var0.getCodeAnalyzer().getDetectedCompiler();
      if (var2 instanceof Or && var3 instanceof abl && var1.WR) {
         for (auu var6 : var0.getInternalMethods()) {
            if (var6.Er() == null) {
               String var7 = var6.getName(false);
               if (var7.startsWith("?")) {
                  String var8 = A(var7);
                  if (var8 != null) {
                     vP.put(var6, var8);
                  }
               }
            }
         }
      }

      Set var23 = var1.A();
      if (var23 != null) {
         for (Couple var25 : var23) {
            String var26 = (String)var25.getFirst();
            Integer var27 = (Integer)var25.getSecond();
            auu var9 = var0.A(var26);
            if (var9 != null && var9.E() != null && var9.E().getCFG().getInstructionCount() == var27) {
               CFG var10 = var9.E().getCFG();
               IInstruction var11 = (IInstruction)var10.getInstructions().get(var27 - 1);
               IFlowInformation var12 = var11.getBreakingFlow(var10.getLastAddress());
               if (var12.isBroken() && var12.getTargets().size() == 1) {
                  ICodePointer var13 = (ICodePointer)var12.getTargets().get(0);
                  auu var14 = var0.A(var13.getAddress());
                  if (var14 != null) {
                     Set var15 = var0.getCodeModel().getReferenceManager().getReferencesTo(var14.E().getMemoryAddress());
                     if (var15 != null) {
                        for (IReference var17 : var15) {
                           if (var17.getFrom().isInternalAddress()) {
                              long var18 = var17.getFrom().getInternalAddress();
                              List var20 = var0.getCodeModel().getContainedRoutineAddresses(var18);
                              if (var20 != null && var20.size() == 1) {
                                 auu var21 = var0.A((Long)var20.get(0));
                                 String var22 = var21.Er();
                                 if (var22 == null) {
                                    var22 = var21.getName(false);
                                 }

                                 if (var0.A(var14.E().getMemoryAddress()) != null) {
                                    var0.undefineItem(var14.E().getMemoryAddress());
                                 }

                                 var0.undefineItem(var21.E().getMemoryAddress());
                                 var0.pC(var21.E().getMemoryAddress(), var22);
                                 var0.getCodeAnalyzer().enqueuePointerForAnalysis(new CodePointer(var21.E().getMemoryAddress()));
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

   private static String A(String var0) {
      File var1 = new File("C:\\Tools\\undname.exe");
      if (!var1.exists()) {
         A.error("Cant find undname.exe");
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
            A.catching(var10);
         }

         return var2;
      }
   }

   private static Map pC(String var0, String var1) {
      File var2 = new File(var0.concat(File.separator).concat(var1).concat(".rtn_to_sign"));
      if (!var2.exists()) {
         return null;
      } else {
         List var3;
         try {
            var3 = IO.readLines(var2);
         } catch (IOException var9) {
            A.error("error when reading routine list file");
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

   public static boolean pC(String var0, String var1, INativeSignaturePackage var2, HE var3) throws JebException, IOException {
      com.pnfsoftware.jeb.corei.parsers.asm.nativesig.K var4;
      if (var3.pC.isIntel()) {
         if (var1.endsWith(".exe")) {
            var4 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.K(ProcessorUtil.toWellKnownUnitType(var3.pC), 1, 0, var3.Er());
         } else {
            var4 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.K(ProcessorUtil.toWellKnownUnitType(var3.pC), 4, 0, var3.Er());
         }
      } else if (var3.pC.isMIPS()) {
         var4 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.K(ProcessorUtil.toWellKnownUnitType(var3.pC), 1, 2, var3.Er());
      } else {
         var4 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.K(ProcessorUtil.toWellKnownUnitType(var3.pC), 1, 0, var3.Er());
      }

      if (var3.ED != null) {
         var4.pC(var3.ED.getPropertyId());
      }

      HeadlessClientContext var5 = com.pnfsoftware.jeb.corei.parsers.asm.nativesig.oP.pC(var4);

      try {
         var5.getEnginesContext().getNativeSignatureDBManager().setActive(false);
         List var6 = com.pnfsoftware.jeb.corei.parsers.asm.nativesig.oP.pC(var5, var0, var1);
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
                  if (!(var11 instanceof C var12)) {
                     List var13 = UnitUtil.findChildrenByType(var11, C.class, false);
                     if (var13 == null || var13.size() != 1) {
                        var62 = new Object[0];
                        return false;
                     }

                     var12 = (C)var13.get(0);
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

                  pC(var12, var3);
                  a var44 = (a)var12.getCodeAnalyzer();
                  String var47;
                  if (var7) {
                     var47 = Paths.get(var11.getName()).getFileName().toString();
                  } else {
                     var47 = var1;
                  }

                  axs var15 = (axs)com.pnfsoftware.jeb.corei.parsers.asm.nativesig.DH.pC(NativeFeatureSignerID.ROUTINE_SIZE, var44);
                  axn var16 = (axn)com.pnfsoftware.jeb.corei.parsers.asm.nativesig.DH.pC(NativeFeatureSignerID.ROUTINE_CODE_HASH, var44);
                  axl var17 = (axl)com.pnfsoftware.jeb.corei.parsers.asm.nativesig.DH.pC(NativeFeatureSignerID.CALLED_ROUTINE_NAME, var44);
                  var17.pC(var3.xC);
                  axq var18 = (axq)com.pnfsoftware.jeb.corei.parsers.asm.nativesig.DH.pC(NativeFeatureSignerID.ROUTINE_1B_CTE, var44);
                  awz var19 = new awz();
                  if (var3.ED instanceof abl) {
                     var19.pC(pC);
                  } else if (var3.ED instanceof abe) {
                     var19.pC(new awk());
                  }

                  axg var20 = new axg();
                  Map var21 = pC(var0, var1);
                  if (var21 != null && var21.size() != 0) {
                     for (Entry var23 : var21.entrySet()) {
                        auu var24 = var12.A((Long)var23.getKey());
                        if (var24 == null) {
                           long var25 = (Long)var23.getKey();
                           INativeContinuousItem var27 = var44.ys().getItemAt(var25);
                           if (var27 instanceof aus) {
                              var44.ys().pC(var27, true);
                           }

                           var44.enqueuePointerForAnalysis(new CodePointer(var25), 1);
                           var44.analyze();
                           var24 = var12.A((Long)var23.getKey());
                           if (var24 == null) {
                              throw new RuntimeException("Error with routine list: no routine at the provided address");
                           }
                        }

                        if (var23.getValue() != null) {
                           var24.E().setName((String)var23.getValue());
                        }
                     }
                  }

                  Iterator var48 = var12.getInternalMethods().iterator();

                  while (true) {
                     auu var50;
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
                              auu var53 = var12.A((Long)var51.getKey());
                              if (var53 == null) {
                                 throw new RuntimeException("problem with routine list creation");
                              }

                              if (!var53.getName(true).equals(var51.getValue())) {
                                 pC(var2, var53.getName(true), (String)var51.getValue());
                              }
                           }
                        }

                        var50 = (auu)var48.next();
                        var52 = null;
                        if (var21 == null || var21.size() == 0) {
                           break;
                        }

                        if (var21.containsKey(var50.E().getMemoryAddress())) {
                           var52 = (String)var21.get(var50.E().getMemoryAddress());
                           break;
                        }
                     }

                     if (var52 == null && ((ph)var12.getCodeModel().getLabelManager()).pC(var50.getName(true))) {
                        kS.pC(var47);
                     } else if (var52 == null && var50.getName(true).startsWith("_ptr_")) {
                        kS.pC(var47);
                     } else {
                        if (var52 == null) {
                           var52 = var50.Er();
                        }

                        if (var52 == null) {
                           var52 = var50.getName(false);
                           if (((ph)var12.getCodeModel().getLabelManager()).pC(var52) && !((ph)var12.getCodeModel().getLabelManager()).pC(var50.getName(true))) {
                              var52 = var50.getName(true);
                           }
                        }

                        if (var3.A(var47, var52)) {
                           kS.pC(var52, var47, sy.Av.pC);
                        } else if (var50.z() == null && !var50.Ek()) {
                           com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD var54;
                           if (var3.z() instanceof com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Av) {
                              NativeSignatureGenerator var26 = NativeSignatureGenerator.getInstance(var3.z());
                              var26.setDefaultStrategy(var3.z());
                              var54 = (com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD)var26.generateSignature(var44, var50, null, null);
                              int var56 = var50.E().getCFG().getInstructionCount();
                              sy.Av var28 = sy.Av.wS;
                              if (pC(var56)) {
                                 var28 = sy.Av.A;
                              } else if (A(var56)) {
                                 var28 = sy.Av.kS;
                              } else if (kS(var56)) {
                                 var28 = sy.Av.wS;
                              } else if (wS(var56)) {
                                 var28 = sy.Av.UT;
                              }

                              kS.pC(var52, var47, var28);
                              kS.pC(var52, var47, var54);
                           } else {
                              if (!(var3.z() instanceof com.pnfsoftware.jeb.corei.parsers.asm.nativesig.vi)) {
                                 throw new RuntimeException(Strings.ff("TBI: config %s", var3.getClass().getCanonicalName()));
                              }

                              ArrayList var55 = new ArrayList();
                              HashSet var57 = new HashSet();
                              ArrayList var58 = new ArrayList();
                              INativeSignature.ConfidenceLevel var29 = INativeSignature.ConfidenceLevel.MEDIUM;
                              boolean var30 = false;
                              int var31 = var50.E().getCFG().getInstructionCount();
                              if (pC(var31) && !var3.kS().contains(var52)) {
                                 sY.add(var52);
                                 kS.pC(var52, var47, sy.Av.A);
                                 continue;
                              }

                              if (A(var31) || pC(var31) && var3.kS().contains(var52)) {
                                 for (CallGraphVertex var34 : var44.ys().getCallGraphManager().getGlobalCallGraph().getCallees(var50, true)) {
                                    if (var34.isInternal()) {
                                       INativeContinuousItem var35 = var44.ys().getItemAt(var34.getInternalAddress().getAddress());
                                       if (var35 != null && !var17.A(var35.getName(true))) {
                                          var30 = true;
                                          break;
                                       }
                                    } else if (!var34.isResolved() && !var17.A(var44.ys().getItemAt(var34.getDereferencedAddress()).getName(true))) {
                                       var30 = true;
                                       break;
                                    }
                                 }

                                 if (var30) {
                                    kS.pC(var52, var47, "unnamed call");
                                    var29 = INativeSignature.ConfidenceLevel.LOW;
                                 }

                                 var15.pC(var50);
                                 var55.addAll(var15.pC());
                                 var15.A();
                                 var16.pC(var50);
                                 var55.addAll(var16.pC());
                                 var16.A();
                                 var17.pC(var50);
                                 var55.addAll(var17.pC());
                                 var17.A();
                                 var18.pC(var50);
                                 var57.addAll(var18.pC());
                                 var18.A();
                                 kS.pC(var52, var47, sy.Av.kS);
                              } else if (kS(var31)) {
                                 var15.pC(var50);
                                 var55.addAll(var15.pC());
                                 var15.A();
                                 var16.pC(var50);
                                 var55.addAll(var16.pC());
                                 var16.A();
                                 var17.pC(var50);
                                 var57.addAll(var17.pC());
                                 var17.A();
                                 kS.pC(var52, var47, sy.Av.wS);
                              } else if (wS(var31)) {
                                 var15.pC(var50);
                                 var55.addAll(var15.pC());
                                 var15.A();
                                 var16.pC(var50);
                                 var55.addAll(var16.pC());
                                 var16.A();
                                 kS.pC(var52, var47, sy.Av.UT);
                              }

                              if (var7) {
                                 var58.add(new awu(var47, var1));
                              } else {
                                 var58.add(new awu(var1));
                              }

                              awt var59 = new awt(var3.ld);
                              var58.add(var59);
                              String var60 = (String)vP.get(var50);
                              if (var60 != null) {
                                 aww var61 = new aww(var60);
                                 var58.add(var61);
                              }

                              var19.pC(var50);
                              var58.addAll(var19.pC());
                              var19.A();
                              var20.pC(var50);
                              var58.addAll(var20.pC());
                              var20.A();
                              var54 = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.KD(
                                 var52, var55, var58, new NativeSignatureFlags(SignatureTargetType.ROUTINE), var29
                              );
                              fI.put(var54, var57);
                              kS.pC(var52, var47, var54);
                              kS.pC(var52, var47, var57);
                              if (var30) {
                                 oT.add(var54);
                              }
                           }

                           if (var54 == null) {
                              throw new RuntimeException("error: null signature");
                           }

                           ((com.pnfsoftware.jeb.corei.parsers.asm.nativesig.qt)var2).pC(var54);
                        }
                     }
                  }
               }
            }

            A();
            return true;
         }
      } finally {
         com.pnfsoftware.jeb.corei.parsers.asm.nativesig.oP.pC(var5, var4);
      }
   }

   static {
      pC.add("_wmain");
      pC.add("_main");
      pC.add("_wWinMain@16");
      pC.add("_WinMain@16");
      pC.add("_DllMain@12");
      pC.add("wmain");
      pC.add("main");
      pC.add("wWinMain");
      pC.add("WinMain");
      pC.add("DllMain");
   }

   public static enum Av {
      pC,
      A,
      kS,
      wS,
      UT;
   }
}
