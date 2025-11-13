package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.output.code.coordinates.ClassCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.FieldCoordinates;
import com.pnfsoftware.jeb.core.units.code.java.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.java.IJavaClass;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGlobalContext;
import com.pnfsoftware.jeb.core.units.code.java.IJavaInstanceField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaNew;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStaticField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeFactory;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.core.units.code.java.JavaReconAnon;
import com.pnfsoftware.jeb.core.units.code.java.JavaReconEnum;
import com.pnfsoftware.jeb.core.units.code.java.JavaReconEnummap;
import com.pnfsoftware.jeb.core.units.code.java.JavaTypeUtil;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Ser
public class bjl extends bin implements IJavaClass {
   private static final ILogger kS = GlobalLog.getLogger(bjr.class);
   @SerId(1)
   private IJavaTypeFactory wS;
   @SerId(2)
   private int UT;
   @SerId(3)
   private int E;
   @SerId(4)
   private boolean sY;
   @SerId(5)
   private int ys;
   @SerId(6)
   private IJavaType ld;
   @SerId(7)
   private IJavaType gp;
   @SerId(8)
   private List oT;
   @SerId(9)
   private List fI;
   @SerId(10)
   private Map WR = new HashMap();
   @SerId(value = 11, deprecated = true)
   @Deprecated
   private List NS;
   @SerId(value = 12, deprecated = true)
   @Deprecated
   private List vP;
   @SerId(value = 13, deprecated = true)
   @Deprecated
   private List xC;
   @SerId(value = 14, deprecated = true)
   @Deprecated
   private List ED;
   @SerId(15)
   @Deprecated
   public int A;
   @Deprecated
   @SerId(16)
   private JavaReconEnum Sc;
   @Deprecated
   @SerId(17)
   private JavaReconAnon ah;
   @Deprecated
   @SerId(18)
   private JavaReconEnummap eP;
   @SerId(19)
   private List UO;
   @SerId(20)
   private List Ab;
   @SerId(21)
   private List rl;
   @SerId(22)
   private List z;

   @SerCustomInitPostGraph
   private void UT() {
      if (this.NS != null) {
         this.UO = new CopyOnWriteArrayList();
         this.NS.forEach(var1 -> this.UO.add(var1.getSignature()));
         this.NS = null;
      }

      if (this.vP != null) {
         this.Ab = new CopyOnWriteArrayList();
         this.vP.forEach(var1 -> this.Ab.add(var1.getSignature()));
         this.vP = null;
      }

      if (this.xC != null) {
         this.rl = new CopyOnWriteArrayList();
         this.xC.forEach(var1 -> this.rl.add(var1.getSignature()));
         this.xC = null;
      }

      if (this.ED != null) {
         this.z = new CopyOnWriteArrayList();
         this.ED.forEach(var1 -> this.z.add(var1.getSignature()));
         this.ED = null;
      }

      if (this.Sc != null) {
         this.setReconEnum(this.Sc);
         this.Sc = null;
      }

      if (this.eP != null) {
         this.setReconEnummap(this.eP);
         this.eP = null;
      }

      if (this.ah != null) {
         this.setReconAnon(this.ah);
         this.ah = null;
      }

      if (this.A != 0) {
         this.addFlags(this.A);
         this.A = 0;
      }
   }

   public bjl(int var1, int var2, IJavaTypeFactory var3, IJavaType var4, boolean var5, IJavaType var6, List var7, int var8) {
      this.wS = var3;
      this.UT = var1;
      this.E = var2;
      this.sY = var5;
      this.ys = var8;
      this.ld = var4;
      this.gp = var6;
      this.oT = var7;
      this.UO = new CopyOnWriteArrayList();
      this.Ab = new CopyOnWriteArrayList();
      this.rl = new CopyOnWriteArrayList();
      this.z = new CopyOnWriteArrayList();
   }

   public boolean A() {
      if (!this.isBuilt()) {
         return false;
      } else {
         ArrayList var1 = new ArrayList(this.rl.size() + this.z.size());
         var1.addAll(this.rl);
         var1.addAll(this.z);

         for (String var3 : var1) {
            bjl var4 = bhu.pC(this, var3);
            if (var4 == null || !var4.A()) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public IJavaGlobalContext getGlobalContext() {
      return this.d_() != null ? ((com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)this.d_()).A() : null;
   }

   public void wS(List var1) {
      this.fI = var1;
   }

   @Override
   public List getAnnotations() {
      return this.fI;
   }

   @Override
   public boolean isExternal() {
      return this.sY;
   }

   public int kS() {
      return this.UT;
   }

   @Override
   public String getName() {
      return this.ld.getName();
   }

   @Override
   public int getAccessFlags() {
      return this.ys;
   }

   @Override
   public boolean isInner() {
      return (this.ys & 1048576) != 0;
   }

   @Override
   public boolean isAnonymous() {
      return (this.ys & 2097152) != 0;
   }

   @Override
   public boolean isStatic() {
      return (this.ys & 8) != 0;
   }

   @Override
   public boolean isInterface() {
      return (this.ys & 512) != 0;
   }

   @Override
   public boolean isEnumeration() {
      return (this.ys & 16384) != 0;
   }

   @Override
   public boolean isAnnotation() {
      return (this.ys & 8192) != 0;
   }

   @Override
   public boolean isSynthetic() {
      return (this.ys & 4096) != 0;
   }

   @Override
   public String getSignature() {
      return this.ld.getSignature();
   }

   @Override
   public IJavaType getType() {
      return this.ld;
   }

   @Override
   public IJavaType getSupertype() {
      return this.gp;
   }

   @Override
   public List getImplementedInterfaces() {
      return this.oT;
   }

   @Override
   public boolean addImport(String var1, String var2) {
      JavaTypeUtil.checkLegalInternalClassname(var2);
      String var3 = (String)this.WR.get(var1);
      if (var3 != null && !var3.equals(var2)) {
         return false;
      } else {
         if (var3 == null) {
            this.WR.put(var1, var2);
         }

         return true;
      }
   }

   @Override
   public String getImport(String var1) {
      return (String)this.WR.get(var1);
   }

   @Override
   public List getInnerClassSignatures() {
      return this.rl;
   }

   @Override
   public List getInnerClasses() {
      ArrayList var1 = new ArrayList();
      this.rl.forEach(var2 -> Lists.addNonNulls(var1, bhu.pC(this, var2)));
      return var1;
   }

   public void A(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         if (!this.rl.contains(var1)) {
            this.rl.add(var1);
         }
      }
   }

   @Override
   public List getAnonymousClassSignatures() {
      return this.z;
   }

   @Override
   public List getAnonymousClasses() {
      ArrayList var1 = new ArrayList();
      this.z.forEach(var2 -> Lists.addNonNulls(var1, bhu.pC(this, var2)));
      return var1;
   }

   public void kS(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         if (!this.z.contains(var1)) {
            this.z.add(var1);
         }
      }
   }

   @Override
   public List getFieldSignatures() {
      return this.UO;
   }

   @Override
   public List getFields() {
      ArrayList var1 = new ArrayList();
      this.UO.forEach(var2 -> Lists.addNonNulls(var1, bhu.A(this, var2)));
      return var1;
   }

   public void wS(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         if (!this.UO.contains(var1)) {
            this.UO.add(var1);
         }
      }
   }

   @Override
   public List getMethodsSignatures() {
      return this.Ab;
   }

   @Override
   public List getMethods() {
      ArrayList var1 = new ArrayList();
      this.Ab.forEach(var2 -> Lists.addNonNulls(var1, bhu.kS(this, var2)));
      return var1;
   }

   public void UT(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         if (!this.Ab.contains(var1)) {
            this.Ab.add(var1);
         }
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.generate(var1, null, 0, false);
   }

   @Override
   public void generate(JavaOutputSink var1, List var2, int var3, boolean var4) {
      if (this.sY) {
         throw new RuntimeException("Cannot generate an external class");
      } else {
         boolean var5 = var1.getTopLevelClass() == null && !this.isInner();
         int var6 = var1.getSortItemsForRendering();
         IDynamicContentManager var7 = var1.getDynamicContentManager();
         this.pC(var1);
         var1.pushContainingClass(this);
         ClassCoordinates var8 = new ClassCoordinates(this.E);
         boolean var9 = !var5 && var2 != null;
         IJavaType var10 = !var9 ? null : (this.oT.size() == 1 ? (IJavaType)this.oT.get(0) : this.gp);
         var1.pushAnonymousBaseType(var10);
         JavaOutputSink.CaptureInfo var11 = null;
         String var12 = "";
         if (var5 && JavaTypeUtil.generatePackageNameStandardRepresentation(this.ld.getName()).length() > 0) {
            var1.appendKeyword(JavaKeyword.PACKAGE);
            var1.space();
            var12 = bhn.A(var1, this.ld);
            var1.append(";");
            var1.eol();
            var1.eol();
         }

         this.WR.clear();
         int var13 = var1.getCurrentLineIndex();
         boolean var14 = this.isAnnotation();
         boolean var15 = this.isInterface();
         boolean var16 = this.isEnumeration();
         boolean var17 = var16 && this.getReconEnum() == null;
         boolean var18 = var16 && this.getReconEnum() != null;
         if (var17 && !var9) {
            var1.appendMultiLineCommentAuto(
               S.L("PARTIAL FAILURE: ENUM SUGARING\nThe enumeration is rendered as-is instead of being sugared into a Java 5 enum."), false, true
            );
         }

         if (!var9) {
            var1.renderPreComment(var8);
            var1.renderInlineComment(var8, true);
            if (this.fI != null && var1.getGenerateAnnotations()) {
               var1.appendAnnotationsList(this.fI, '\n');
            }

            var1.recordGeneratedDecompilable(this.getSignature());
            int var19 = this.ys;
            if (var18) {
               var19 &= -17;
            }

            if (var15) {
               var19 &= -1025;
               var19 &= -9;
            }

            if (var17) {
               var19 &= -16385;
            }

            JavaKeyword.generateClassAccessFlags(var1, var19, -1);
            if (var17 || !var16 && !this.isInterface() && !this.isAnnotation()) {
               var1.appendKeyword(JavaKeyword.CLASS);
               var1.space();
            }

            bhn.pC(var1, this.ld, false, true, 0L);
            if (!this.gp.getName().equals("Ljava/lang/Object;") && (!var16 || var17 || var18 && !this.gp.getName().equals("Ljava/lang/Enum;"))) {
               var1.space();
               var1.appendKeyword(JavaKeyword.EXTENDS);
               var1.space();
               bhn.pC(var1, this.gp);
            }

            if (!this.oT.isEmpty() && !var14) {
               if (var6 == 1) {
                  Collections.sort(this.oT, new bjm(this));
               }

               var1.space();
               if (var15) {
                  var1.appendKeyword(JavaKeyword.EXTENDS);
               } else {
                  var1.appendKeyword(JavaKeyword.IMPLEMENTS);
               }

               int var20 = 0;

               for (IJavaType var22 : this.oT) {
                  if (var20 == 0) {
                     var1.space();
                  } else {
                     var1.append(", ");
                  }

                  bhn.pC(var1, var22);
                  var20++;
               }
            }

            var1.setEolCoordinates(var8);
         } else if (!var4) {
            var1.recordGeneratedDecompilable(var10.getSignature());
            bhn.pC(var1, var10);
            if (var3 > var2.size()) {
               RuntimeException var41 = new RuntimeException(
                  Strings.ff("Invalid arg range for anonymous class: start=%d, size=%d, has_recon=%b", var3, var2.size(), this.getReconAnon() != null)
               );
               com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var41, this.getType().getSignature());
               var3 = 0;
            }

            var1.paren();
            if (this.getReconAnon() == null) {
               int var43 = 0;

               for (IJavaExpression var51 : var2.subList(var3, var2.size())) {
                  if (var43 >= 1) {
                     var1.append(", ");
                  }

                  var51.generate(var1);
                  var43++;
               }
            } else {
               var11 = new JavaOutputSink.CaptureInfo();
               var11.initializerArgs = var2.subList(var3, var2.size());
               int var42 = 0;

               for (int var49 : this.getReconAnon().getArgumentIndicesUsedBySuper()) {
                  if (var49 >= var11.initializerArgs.size()) {
                     var1.append(Strings.ff("/*ERROR_MISSING_ARG_%d/*", var49));
                     com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(
                        new RuntimeException("Missing anonymous argument " + var49), this.getSignature(), -1, null, true
                     );
                  } else {
                     IJavaExpression var54 = (IJavaExpression)var11.initializerArgs.get(var49);
                     if (var42 >= 1) {
                        var1.append(", ");
                     }

                     var54.generate(var1);
                  }

                  var42++;
               }

               int var46 = 0;

               for (IJavaExpression var55 : var11.initializerArgs) {
                  if (this.getReconAnon().isCapturedToSyntheticField(var46)) {
                     IJavaInstanceField var23 = this.getReconAnon().getCaptureSite(var46);
                     var11.synth.put(var23, var55);
                  }

                  var46++;
               }

               var1.pushAnonymousCaptureInfo(var11);
            }

            var1.parenClose();
         }

         String[] var44 = new String[1];
         boolean var48 = false;
         if (!var1.getDisregardCollapse()) {
            var48 = var7 != null && var7.isCollapsed(this.getSignature(), var44);
         }

         if (var48) {
            var1.getCurrentLine().addFlags(1);
            var1.space();
            var1.append("[...]");
            String var52 = var44[0];
            if (!Strings.isBlank(var52)) {
               var1.appendCommentAuto(" // " + var52);
            }

            var1.eol();
         } else {
            var1.space();
            var1.brace();
            var1.eol();
            var1.incrementIndentationLevel();
            List var53 = this.getInnerClasses();
            if (var6 == 1) {
               Collections.sort(var53, new bjn(this));
            }

            for (IJavaClass var58 : var53) {
               var58.generate(var1);
            }

            List var57 = this.getFields();
            HashSet var59 = new HashSet();
            List var24 = this.getMethods();
            HashSet var25 = new HashSet();
            if (this.getReconEnum() != null) {
               int var26 = 0;

               for (JavaReconEnum.ECst var28 : this.getReconEnum().getEnumeratedConstants()) {
                  if (var26 >= 1) {
                     var1.append(",");
                     var1.eol();
                  }

                  IJavaField var29 = null;
                  if (var28.getCompilerGeneratedStaticField() != null) {
                     var29 = var28.getCompilerGeneratedStaticField().getField();
                  }

                  if (var29 != null) {
                     FieldCoordinates var30 = new FieldCoordinates(((bjq)var29).A());
                     var1.setEolCoordinates(var30);
                     List var31 = var29.getAnnotations();
                     if (var31 != null && !var31.isEmpty() && var1.getGenerateAnnotations()) {
                        var1.appendAnnotationsList(var31, '\n');
                     }

                     var29.generateName(var1, true, var28.getName(), false);
                  } else {
                     var1.append(var28.getName());
                     var1.onEolAddComment(S.L("this enumerated field was removed"));
                  }

                  if (!var28.getArgumentList().isEmpty()) {
                     var1.paren();
                     int var68 = 0;

                     for (IJavaExpression var32 : var28.getArgumentList()) {
                        if (var68 >= 1) {
                           var1.append(", ");
                        }

                        var32.generate(var1);
                        var68++;
                     }

                     var1.parenClose();
                  }

                  if (var28.getNewExpressionForSubEnum() != null) {
                     IJavaNew var69 = var28.getNewExpressionForSubEnum();
                     IJavaClass var74 = var69.getGeneratedAnonymousClass(var1, null);
                     if (var74 == null) {
                        RuntimeException var78 = new RuntimeException("Custom enum code block not generated: " + var28.getName());
                        com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var78, this.getType().getSignature());
                        var1.space();
                        var1.appendCommentAuto(Strings.ff(S.L("/* WARNING! Enum subclass not generated: %s */"), var69.getType()));
                     } else {
                        var74.generate(var1, new ArrayList(), 0, true);
                     }
                  }

                  if (var29 != null) {
                     var59.add(var29.getSignature());
                  }

                  var26++;
               }

               var1.append(";");
               var1.eol();
               var1.eol();
               if (this.getReconEnum().getValuesArray() != null) {
                  IJavaStaticField var61 = this.getReconEnum().getValuesArray();
                  var59.add(var61.getFieldSignature());
               }
            }

            if (var6 == 1) {
               Collections.sort(var57, new bjo(this));
            }

            int var60 = 0;

            for (IJavaField var64 : var57) {
               if (!var59.contains(var64.getSignature())
                  && (var1.getGenerateSyntheticFields() || !var64.isOptionalRendering() && !var64.getName().startsWith("this$"))) {
                  var64.generate(var1);
                  var60++;
               }
            }

            if (var60 >= 1) {
               var1.eol();
            }

            if (var6 == 1) {
               Collections.sort(var24, new bjp(this, var1));
            }

            IJavaMethod var63 = null;
            int var65 = 0;

            for (IJavaMethod var70 : var24) {
               if (var70.isClassConstructor()) {
                  if (var70.getVisibleParameterCount() == 0) {
                     var63 = var70;
                  }

                  var65++;
               }
            }

            if (var65 == 1 && var63 != null && var63.isEmpty()) {
               var25.add(var63.getSignature());
            }

            int var67 = 0;
            Iterator var71 = var24.iterator();

            label349:
            while (true) {
               int var33;
               IJavaMethod var75;
               boolean var79;
               while (true) {
                  if (!var71.hasNext()) {
                     List var72 = var1.getGeneratedAnon(this);

                     for (IJavaClass var80 : this.getAnonymousClasses()) {
                        if (!var72.contains(var80) && var80.getReconEnummap() == null) {
                           var1.eol();
                           var80.generate(var1);
                        }
                     }

                     if (var11 != null) {
                        var1.popAnonymousCaptureInfo();
                     }

                     var1.decrementIndentationLevel();
                     var1.braceClose();
                     if (!var9) {
                        var1.eol();
                        var1.eol();
                     }

                     if (var5 && this.WR.size() > 0) {
                        ArrayList var77 = new ArrayList();

                        for (String var83 : this.WR.values()) {
                           String var85 = JavaTypeUtil.generatePackageNameStandardRepresentation(var83);
                           if (!var85.equals("java.lang") && !var85.equals(var12)) {
                              var77.add(var83);
                           }
                        }

                        Collections.sort(var77);
                        if (var77.size() > 0) {
                           for (String var84 : var77) {
                              var1.appendKeyword(JavaKeyword.IMPORT);
                              var1.space();
                              bhn.pC(var1, this.wS.createType(var84), true, false, 0L);
                              var1.append(";");
                              var1.eol();
                              var1.moveLastLine(var13);
                              var13++;
                           }

                           var1.eol();
                           var1.moveLastLine(var13);
                        }
                     }
                     break label349;
                  }

                  var75 = (IJavaMethod)var71.next();
                  if (!var25.contains(var75.getSignature())) {
                     var79 = false;
                     var33 = 0;
                     if (!var75.isConstructor() || !var9) {
                        break;
                     }

                     if (!var75.getBody().isEmpty()) {
                        if (var11 != null) {
                           List var34 = var75.getVisibleParameters();
                           int var35 = 0;

                           for (IJavaDefinition var37 : var34) {
                              if (var35 >= var11.initializerArgs.size()) {
                                 RuntimeException var86 = new RuntimeException("Missing captured arguments");
                                 com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var86, this.getType().getSignature(), -1, null, true);
                                 break;
                              }

                              IJavaExpression var38 = (IJavaExpression)var11.initializerArgs.get(var35);
                              biq var39 = (biq)this.getGlobalContext().createAssignment(var37, var38);
                              var39.kS = S.L("captured argument");
                              if (!(var38 instanceof bix)) {
                                 var39.kS = var39.kS + " " + S.L("(potential naming conflict with outer method variables; consider manual renaming)");
                              }

                              var75.getBody().insert(var35, var39);
                              var35++;
                           }

                           var33 = var34.size();
                        }

                        var79 = true;
                        break;
                     }
                  }
               }

               if (var1.getGenerateSyntheticMethods() || !var75.isOptionalRendering()) {
                  if (var67 >= 1) {
                     var1.eol();
                  }

                  var75.generate(var1, var79);

                  while (var33-- > 0) {
                     var75.getBody().remove(0);
                  }

                  var67++;
               }
            }
         }

         var1.popContainingClass();
         var1.popAnonymousBaseType();
         this.A(var1);
      }
   }

   @Override
   public List getSubElements() {
      return bhr.pC();
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      return false;
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Class;
   }

   @Override
   public int hashCode() {
      return super.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1;
   }

   @Override
   public String toString() {
      return "class:" + this.getName();
   }

   public bjl wS() {
      return this;
   }
}
