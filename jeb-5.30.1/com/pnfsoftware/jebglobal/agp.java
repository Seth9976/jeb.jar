package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.NativeCoordinates;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CKeyword;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCompound;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifierManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabelFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICVisitor;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Ser
public class agp extends aga implements ICMethod {
   private static final StructuredLogger gO = aeg.q(agp.class);
   @SerId(1)
   private ICIdentifierManager nf;
   @SerId(2)
   private ICLabelFactory gP;
   @SerId(3)
   private String za;
   @SerId(4)
   private ICType lm;
   @SerId(5)
   private List zz = new ArrayList();
   @SerId(6)
   private List JY = new ArrayList();
   @SerId(7)
   private ICBlock HF;
   @SerId(8)
   private List LK;
   @SerId(9)
   private boolean io;

   public agp(ICGlobalContext var1, String var2) {
      super(var1, var2);
      this.nf = var1.createLocalIdentifierManager();
      this.gP = var1.createLabelFactory();
      this.HF = var1.getElementFactory().createBlock();
   }

   public void q(
      boolean var1,
      int var2,
      ICIdentifierManager var3,
      ICLabelFactory var4,
      ICType var5,
      String var6,
      List var7,
      List var8,
      boolean var9,
      int var10,
      Long var11
   ) {
      this.xK = var1;
      this.Dw = var2;
      this.nf = (agj)var3;
      this.gP = (ago)var4;
      this.lm = var5;
      this.za = var6 == null ? this.q : var6;
      this.zz = var8 == null ? new ArrayList() : new ArrayList(var8);
      this.JY = var7 == null ? new ArrayList() : new ArrayList(var7);
      this.io = var9;
      this.RF = var10;
      if (this.HF == null) {
         this.HF = new afk();
      } else {
         this.HF.reset();
      }

      this.addPhysicalOffset(var11);
      this.setStatusCode(0);
   }

   public agp RF() {
      return this;
   }

   @Override
   public ICIdentifierManager getIdentifierManager() {
      return this.nf;
   }

   public void q(agj var1) {
      this.nf = var1;
   }

   @Override
   public ICLabelFactory getLabelFactory() {
      return this.gP;
   }

   public void q(ago var1) {
      this.gP = var1;
   }

   public void q(int var1) {
      this.Dw = var1;
   }

   @Override
   public List getParameters() {
      return this.zz;
   }

   @Override
   public void addParameter(ICDecl var1) {
      this.addParameter(this.zz.size(), var1);
   }

   @Override
   public void addParameter(int var1, ICDecl var2) {
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         this.zz.add(var1, var2);
      }
   }

   @Override
   public void removeParameter(int var1) {
      this.zz.remove(var1);
   }

   @Override
   public List getReturnTypes() {
      return this.JY;
   }

   @Override
   public ICType getReturnType() {
      return this.JY.isEmpty() ? this.getTypeFactory().getVoid() : (ICType)this.JY.get(0);
   }

   @Override
   public ICType getClassType() {
      return this.lm;
   }

   @Override
   public String getName() {
      return this.za != null ? this.za : this.getAddress();
   }

   @Override
   public String getTargetName() {
      return this.getAddress();
   }

   public void q(boolean var1) {
      this.xK = var1;
   }

   @Override
   public ICBlock getBody() {
      return this.HF;
   }

   @Override
   public boolean isEmpty() {
      return this.HF.isEmpty();
   }

   public void q(ICStatement var1) {
      this.addStatement(var1);
   }

   @Override
   public void addStatement(ICStatement var1) {
      this.HF.add(var1);
   }

   @Override
   public ICStatement getLastStatement() {
      return this.HF.getLast();
   }

   @Override
   public void generate(COutputSink var1) {
      if (!this.xK) {
         ICClass var2 = var1.getCurrentContainingClass();
         this.q(var1);
         var1.pushContainingMethod(this);
         if (this.Uv == 4) {
            var1.appendAndRecord(
               cvm.q(
                  new byte[]{
                     -106,
                     0,
                     15,
                     115,
                     39,
                     21,
                     13,
                     9,
                     69,
                     68,
                     1,
                     6,
                     12,
                     2,
                     29,
                     25,
                     5,
                     13,
                     21,
                     29,
                     6,
                     1,
                     78,
                     13,
                     13,
                     114,
                     55,
                     3,
                     20,
                     23,
                     22,
                     27,
                     72,
                     84,
                     28,
                     1,
                     26,
                     83,
                     86,
                     31,
                     12,
                     18,
                     87,
                     84,
                     27,
                     79,
                     82,
                     23,
                     72,
                     73,
                     1,
                     6,
                     12,
                     2,
                     29,
                     25,
                     5,
                     9,
                     69,
                     84,
                     28,
                     1,
                     26,
                     83,
                     67,
                     12,
                     11,
                     1
                  },
                  1,
                  185
               ),
               ItemClassIdentifiers.COMMENT
            );
            var1.eol();
         }

         IDynamicContentManager var3 = var1.getDynamicContentManager();
         if (var3 != null && var2 == null) {
            String var4 = var3.getPackageOfMethod(this.Dw);
            if (var4 != null) {
               var1.appendAndRecord("// Package: " + var4, ItemClassIdentifiers.COMMENT);
               var1.eol();
               var1.eol();
            }
         }

         this.xK(var1);
         MethodCoordinates var8 = new MethodCoordinates(this.Dw);
         int var5 = var1.setCurrentMethodIndex(this.Dw);
         var1.renderPreComment(var8);
         this.Dw(var1);
         boolean var6 = false;
         if (var3 != null) {
            String var7 = var3.getComment(var8);
            if (var7 != null && !var7.isEmpty()) {
               var1.setEolComment(var7);
            }
         }

         var1.setEolCoordinates(var8);
         if (this.gO()) {
            var1.append(";");
         } else {
            if (!var6) {
               var1.append(" ");
            }

            if (this.Uv != 0 && this.Uv != 4) {
               var1.brace();
               var1.eol();
               var1.incrementIndentationLevel();
               if (this.Uv == 1) {
                  var1.appendCommentAuto("// Uninitialized method");
               } else if (this.Uv == 2) {
                  var1.appendCommentAuto(cvm.q(new byte[]{86, 0, 15, 100, 33, 6, 12, 2, 29, 25, 5, 13, 21, 29, 6, 1, 78, 69, 23, 0, 29, 29}, 1, 121));
               } else if (this.Uv == 3) {
                  var1.appendComment(
                     cvm.q(
                        new byte[]{
                           108,
                           64,
                           80,
                           61,
                           55,
                           36,
                           40,
                           72,
                           34,
                           101,
                           122,
                           48,
                           96,
                           111,
                           127,
                           25,
                           117,
                           122,
                           97,
                           105,
                           102,
                           113,
                           102,
                           125,
                           99,
                           110,
                           111,
                           95,
                           65,
                           55,
                           4,
                           12,
                           83,
                           65,
                           3,
                           1,
                           91,
                           7,
                           29,
                           68,
                           73,
                           3,
                           18,
                           83,
                           65,
                           8,
                           9,
                           29,
                           76,
                           13,
                           4,
                           23,
                           10,
                           30,
                           94,
                           73,
                           45,
                           9,
                           8
                        },
                        2,
                        127
                     )
                  );
               } else {
                  var1.append(cvm.q(new byte[]{108, 64, 80, 44, 28, 2, 9, 7, 3, 78, 8, 6, 91, 82, 94, 75}, 2, 29));
               }

               var1.eol();
               var1.decrementIndentationLevel();
               var1.braceClose();
            } else {
               this.HF.generateHeader(var1);
               this.HF.generateBody(var1, true);
               this.HF.generateFooter(var1);
            }
         }

         var1.eol();
         var1.setCurrentMethodIndex(var5);
         var1.popContainingMethod();
         this.RF(var1);
      }
   }

   private void Dw(COutputSink var1) {
      if (!var1.getSourceCustomizer().generateMethodDeclarationLine(this, var1)) {
         ICClass var2 = var1.getCurrentContainingClass();
         if (var2 != null) {
            if (this.oW()) {
               CKeyword.appendAccessKeyword(var1, CKeyword.VIRTUAL);
               var1.space();
            } else if (this.Uv()) {
               CKeyword.appendAccessKeyword(var1, CKeyword.STATIC);
               var1.space();
            }
         }

         if (this.Dw()) {
            this.generateName(var1, true);
         } else {
            if (this.getReturnTypes().size() <= 1) {
               this.getReturnType().generate(var1);
            } else {
               var1.paren();
               int var3 = 0;

               for (ICType var5 : this.getReturnTypes()) {
                  if (var3 >= 1) {
                     var1.append(", ");
                  }

                  var5.generate(var1);
                  var3++;
               }

               var1.parenClose();
            }

            var1.append(" ");
            this.generateName(var1, true);
         }

         int var6 = 0;
         var1.paren();

         int var8;
         for (var8 = var6; var8 < this.zz.size(); var8++) {
            if (var8 > var6) {
               var1.append(", ");
            }

            ((ICDecl)this.zz.get(var8)).generate(var1);
         }

         if (this.io) {
            if (var8 > var6) {
               var1.append(", ");
            }

            var1.append("...");
         }

         var1.parenClose();
         if (this.LK != null && !this.LK.isEmpty()) {
            var1.space();
            var1.appendKeyword(CKeyword.THROWS);
            var6 = 0;

            for (ICType var10 : this.LK) {
               if (var6 >= 1) {
                  var1.append(",");
               }

               var1.space();
               var10.generate(var1);
               var6++;
            }
         }
      }
   }

   private boolean Dw() {
      return (this.RF & 65536) != 0;
   }

   private boolean Uv() {
      return (this.RF & 8) != 0;
   }

   private boolean oW() {
      return (this.RF & 268435456) != 0;
   }

   private boolean gO() {
      return (this.RF & 1024) != 0;
   }

   @Override
   public void generateName(COutputSink var1, boolean var2) {
      String var3 = this.getName();
      long var4 = 0L;
      IDynamicContentManager var6 = var1.getDynamicContentManager();
      if (var6 != null && this.getPhysicalOffset() != null) {
         NativeCoordinates var7 = new NativeCoordinates(this.getPhysicalOffset());
         String var8 = var6.getMethodName(var7);
         if (var8 != null) {
            var4 = var6.getLabelItemId(var7);
            var3 = var8;
         }
      }

      var1.appendAndRecord(var3, ItemClassIdentifiers.METHOD_NAME, var4, var2 ? 1 : 0);
   }

   @Override
   public boolean insertAtOffset(long var1, ICStatement var3) {
      return this.HF.insertAt(var1, var3);
   }

   @Override
   public List toFlatList() {
      return this.HF.generateFlatList();
   }

   @Override
   public void fromFlatList(List var1) {
      ICStatement var2 = (ICStatement)var1.get(0);
      int[] var3 = new int[1];
      ICStatement var4 = ((ahl)var2).q(var1, 0, var3);
      if (var3[0] != var1.size()) {
         throw new RuntimeException();
      } else {
         this.HF = (ICBlock)var4;
      }
   }

   public void xK() {
      HashSet var1 = new HashSet();
      if (!this.q(this.HF, var1)) {
         throw new RuntimeException();
      }
   }

   private boolean q(ICBlock var1, Set var2) {
      for (ICStatement var4 : var1) {
         if (CollectionUtil.containsReference(var2, var4)) {
            return false;
         }

         var2.add(var4);
         if (var4 instanceof ICCompound) {
            for (ICBlock var6 : ((ICCompound)var4).getBlocks()) {
               if (!this.q(var6, var2)) {
                  return false;
               }
            }
         }
      }

      return true;
   }

   @Override
   public List getStatements() {
      ArrayList var1 = new ArrayList();
      this.q(this.HF, var1);
      return var1;
   }

   private void q(ICBlock var1, List var2) {
      for (ICStatement var4 : var1) {
         var2.add(var4);
         if (var4 instanceof ICCompound) {
            for (ICBlock var6 : ((ICCompound)var4).getBlocks()) {
               this.q(var6, var2);
            }
         }
      }
   }

   @Override
   public boolean deleteStatement(ICStatement var1) {
      int var2 = this.q(this.HF, var1);
      if (var2 >= 2) {
         throw new RuntimeException("Statement deleted more than once, the method is not valid: " + var1);
      } else {
         return var2 == 1;
      }
   }

   private int q(ICBlock var1, ICStatement var2) {
      int var3 = 0;
      int var4 = 0;

      while (var4 < var1.size()) {
         ICStatement var5 = var1.get(var4);
         if (var5 == var2) {
            var1.remove(var4);
            var3++;
         } else {
            if (var5 instanceof ICCompound) {
               for (ICBlock var7 : ((ICCompound)var5).getBlocks()) {
                  var3 += this.q(var7, var2);
               }
            }

            var4++;
         }
      }

      return var3;
   }

   @Override
   public List getSubElements() {
      List var1 = ahf.q(this.zz);
      return ahf.q(var1, this.HF);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      for (int var3 = 0; var3 < this.zz.size(); var3++) {
         if (this.zz.get(var3) == var1) {
            if (!(var2 instanceof ICDecl)) {
               return false;
            }

            this.zz.set(var3, (ICDecl)var2);
            return true;
         }
      }

      if (this.HF != var1) {
         return false;
      } else if (!(var2 instanceof ICBlock)) {
         return false;
      } else {
         this.HF = (ICBlock)var2;
         return true;
      }
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Method;
   }

   @Override
   public String toString() {
      return "method:" + this.getAddress();
   }

   @Override
   public boolean visitStatements(ICVisitor var1) {
      CVisitResults var2 = new CVisitResults();
      q(var1, this.HF, this, var2);
      return var2.isVisitedSuccessfully();
   }

   private static void q(ICVisitor var0, ICElement var1, ICElement var2, CVisitResults var3) {
      var3.currentNode = var1;
      if (var1 instanceof ICStatement) {
         var0.process(var1, var2, var3);
      }

      if (!var3.isInterruptedVisit()) {
         if (var3.skipVisitingChildren) {
            var3.skipVisitingChildren = false;
         } else {
            var1 = (ICElement)var3.currentNode;
            var3.pushParent(var1);
            int var4 = 0;

            for (ICElement var6 : var1.getSubElements()) {
               var3.visitedChildPosition = var4;
               q(var0, var6, var1, var3);
               if (var3.isInterruptedVisit()) {
                  return;
               }

               var4++;
            }

            var3.popParent();
         }
      }
   }
}
