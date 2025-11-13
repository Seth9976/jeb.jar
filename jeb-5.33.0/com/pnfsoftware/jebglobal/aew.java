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
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class aew extends aeh implements ICMethod {
   private static final StructuredLogger sY = aco.pC(aew.class);
   @SerId(1)
   private ICIdentifierManager ys;
   @SerId(2)
   private ICLabelFactory ld;
   @SerId(3)
   private String gp;
   @SerId(4)
   private ICType oT;
   @SerId(5)
   private List fI = new ArrayList();
   @SerId(6)
   private List WR = new ArrayList();
   @SerId(7)
   private ICBlock NS;
   @SerId(8)
   private List vP;
   @SerId(9)
   private boolean xC;

   public aew(ICGlobalContext var1, String var2) {
      super(var1, var2);
      this.ys = var1.createLocalIdentifierManager();
      this.ld = var1.createLabelFactory();
      this.NS = var1.getElementFactory().createBlock();
   }

   public void pC(
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
      this.kS = var1;
      this.wS = var2;
      this.ys = (aeq)var3;
      this.ld = (aev)var4;
      this.oT = var5;
      this.gp = var6 == null ? this.pC : var6;
      this.fI = var8 == null ? new ArrayList() : new ArrayList(var8);
      this.WR = var7 == null ? new ArrayList() : new ArrayList(var7);
      this.xC = var9;
      this.A = var10;
      if (this.NS == null) {
         this.NS = new adr();
      } else {
         this.NS.reset();
      }

      this.addPhysicalOffset(var11);
      this.setStatusCode(0);
   }

   public aew A() {
      return this;
   }

   @Override
   public ICIdentifierManager getIdentifierManager() {
      return this.ys;
   }

   @Override
   public ICLabelFactory getLabelFactory() {
      return this.ld;
   }

   @Override
   public List getParameters() {
      return this.fI;
   }

   @Override
   public void addParameter(ICDecl var1) {
      this.addParameter(this.fI.size(), var1);
   }

   @Override
   public void addParameter(int var1, ICDecl var2) {
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         this.fI.add(var1, var2);
      }
   }

   @Override
   public void removeParameter(int var1) {
      this.fI.remove(var1);
   }

   @Override
   public List getReturnTypes() {
      return this.WR;
   }

   @Override
   public ICType getReturnType() {
      return this.WR.isEmpty() ? this.getTypeFactory().getVoid() : (ICType)this.WR.get(0);
   }

   @Override
   public ICType getClassType() {
      return this.oT;
   }

   @Override
   public String getName() {
      return this.gp != null ? this.gp : this.getAddress();
   }

   @Override
   public String getTargetName() {
      return this.getAddress();
   }

   @Override
   public ICBlock getBody() {
      return this.NS;
   }

   @Override
   public boolean isEmpty() {
      return this.NS.isEmpty();
   }

   @Override
   public void addStatement(ICStatement var1) {
      this.NS.add(var1);
   }

   @Override
   public ICStatement getLastStatement() {
      return this.NS.getLast();
   }

   @Override
   public void generate(COutputSink var1) {
      if (!this.kS) {
         ICClass var2 = var1.getCurrentContainingClass();
         this.pC(var1);
         var1.pushContainingMethod(this);
         if (this.UT == 4) {
            var1.appendAndRecord(
               ckx.pC(
                  new byte[]{
                     108,
                     64,
                     80,
                     42,
                     6,
                     8,
                     11,
                     13,
                     84,
                     68,
                     77,
                     0,
                     70,
                     77,
                     65,
                     80,
                     85,
                     82,
                     88,
                     73,
                     93,
                     94,
                     18,
                     24,
                     12,
                     114,
                     42,
                     20,
                     19,
                     6,
                     31,
                     13,
                     0,
                     21,
                     6,
                     13,
                     92,
                     79,
                     4,
                     73,
                     12,
                     3,
                     83,
                     84,
                     14,
                     70,
                     20,
                     12,
                     65,
                     13,
                     4,
                     23,
                     10,
                     30,
                     94,
                     73,
                     45,
                     9,
                     76,
                     84,
                     26,
                     0,
                     20,
                     72,
                     23,
                     28,
                     68,
                     23
                  },
                  2,
                  175
               ),
               ItemClassIdentifiers.COMMENT
            );
            var1.eol();
         }

         IDynamicContentManager var3 = var1.getDynamicContentManager();
         if (var3 != null && var2 == null) {
            String var4 = var3.getPackageOfMethod(this.wS);
            if (var4 != null) {
               var1.appendAndRecord("// Package: " + var4, ItemClassIdentifiers.COMMENT);
               var1.eol();
               var1.eol();
            }
         }

         this.kS(var1);
         MethodCoordinates var8 = new MethodCoordinates(this.wS);
         int var5 = var1.setCurrentMethodIndex(this.wS);
         var1.renderPreComment(var8);
         this.wS(var1);
         boolean var6 = false;
         if (var3 != null) {
            String var7 = var3.getComment(var8);
            if (var7 != null && !var7.isEmpty()) {
               var1.setEolComment(var7);
            }
         }

         var1.setEolCoordinates(var8);
         if (this.E()) {
            var1.append(";");
         } else {
            if (!var6) {
               var1.append(" ");
            }

            if (this.UT != 0 && this.UT != 4) {
               var1.brace();
               var1.eol();
               var1.incrementIndentationLevel();
               if (this.UT == 1) {
                  var1.appendCommentAuto("// Uninitialized method");
               } else if (this.UT == 2) {
                  var1.appendCommentAuto(ckx.pC(new byte[]{108, 64, 80, 61, 23, 10, 8, 5, 4, 73, 68, 2, 93, 73, 94, 87, 25, 86, 94, 82, 93, 66}, 2, 95));
               } else if (this.UT == 3) {
                  var1.appendComment(
                     ckx.pC(
                        new byte[]{
                           85,
                           0,
                           15,
                           100,
                           1,
                           8,
                           2,
                           111,
                           118,
                           19,
                           23,
                           1,
                           26,
                           6,
                           1,
                           110,
                           108,
                           5,
                           4,
                           4,
                           29,
                           21,
                           21,
                           29,
                           6,
                           1,
                           110,
                           13,
                           13,
                           116,
                           60,
                           1,
                           26,
                           83,
                           77,
                           8,
                           17,
                           28,
                           7,
                           11,
                           68,
                           87,
                           22,
                           18,
                           83,
                           78,
                           1,
                           27,
                           84,
                           68,
                           1,
                           6,
                           12,
                           2,
                           29,
                           25,
                           5,
                           9,
                           1
                        },
                        1,
                        122
                     )
                  );
               } else {
                  var1.append(ckx.pC(new byte[]{118, 0, 15, 117, 59, 5, 5, 1, 24, 25, 78, 69, 23, 0, 29, 29}, 1, 89));
               }

               var1.eol();
               var1.decrementIndentationLevel();
               var1.braceClose();
            } else {
               this.NS.generateHeader(var1);
               this.NS.generateBody(var1, true);
               this.NS.generateFooter(var1);
            }
         }

         var1.eol();
         var1.setCurrentMethodIndex(var5);
         var1.popContainingMethod();
         this.A(var1);
      }
   }

   private void wS(COutputSink var1) {
      if (!var1.getSourceCustomizer().generateMethodDeclarationLine(this, var1)) {
         ICClass var2 = var1.getCurrentContainingClass();
         if (var2 != null) {
            if (this.UT()) {
               CKeyword.appendAccessKeyword(var1, CKeyword.VIRTUAL);
               var1.space();
            } else if (this.wS()) {
               CKeyword.appendAccessKeyword(var1, CKeyword.STATIC);
               var1.space();
            }
         }

         if (this.kS()) {
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
         for (var8 = var6; var8 < this.fI.size(); var8++) {
            if (var8 > var6) {
               var1.append(", ");
            }

            ((ICDecl)this.fI.get(var8)).generate(var1);
         }

         if (this.xC) {
            if (var8 > var6) {
               var1.append(", ");
            }

            var1.append("...");
         }

         var1.parenClose();
         if (this.vP != null && !this.vP.isEmpty()) {
            var1.space();
            var1.appendKeyword(CKeyword.THROWS);
            var6 = 0;

            for (ICType var10 : this.vP) {
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

   private boolean kS() {
      return (this.A & 65536) != 0;
   }

   private boolean wS() {
      return (this.A & 8) != 0;
   }

   private boolean UT() {
      return (this.A & 268435456) != 0;
   }

   private boolean E() {
      return (this.A & 1024) != 0;
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
      return this.NS.insertAt(var1, var3);
   }

   @Override
   public List toFlatList() {
      return this.NS.generateFlatList();
   }

   @Override
   public void fromFlatList(List var1) {
      ICStatement var2 = (ICStatement)var1.get(0);
      int[] var3 = new int[1];
      ICStatement var4 = ((afs)var2).pC(var1, 0, var3);
      if (var3[0] != var1.size()) {
         throw new RuntimeException();
      } else {
         this.NS = (ICBlock)var4;
      }
   }

   @Override
   public List getStatements() {
      ArrayList var1 = new ArrayList();
      this.pC(this.NS, var1);
      return var1;
   }

   private void pC(ICBlock var1, List var2) {
      for (ICStatement var4 : var1) {
         var2.add(var4);
         if (var4 instanceof ICCompound) {
            for (ICBlock var6 : ((ICCompound)var4).getBlocks()) {
               this.pC(var6, var2);
            }
         }
      }
   }

   @Override
   public boolean deleteStatement(ICStatement var1) {
      int var2 = this.pC(this.NS, var1);
      if (var2 >= 2) {
         throw new RuntimeException("Statement deleted more than once, the method is not valid: " + var1);
      } else {
         return var2 == 1;
      }
   }

   private int pC(ICBlock var1, ICStatement var2) {
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
                  var3 += this.pC(var7, var2);
               }
            }

            var4++;
         }
      }

      return var3;
   }

   @Override
   public List getSubElements() {
      List var1 = afm.pC(this.fI);
      return afm.pC(var1, this.NS);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      for (int var3 = 0; var3 < this.fI.size(); var3++) {
         if (this.fI.get(var3) == var1) {
            if (!(var2 instanceof ICDecl)) {
               return false;
            }

            this.fI.set(var3, (ICDecl)var2);
            return true;
         }
      }

      if (this.NS != var1) {
         return false;
      } else if (!(var2 instanceof ICBlock)) {
         return false;
      } else {
         this.NS = (ICBlock)var2;
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
      pC(var1, this.NS, this, var2);
      return var2.isVisitedSuccessfully();
   }

   private static void pC(ICVisitor var0, ICElement var1, ICElement var2, CVisitResults var3) {
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
               pC(var0, var6, var1, var3);
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
