package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.List;

@Ser
public class Dd implements ICExpression {
   @SerId(1)
   private ICExpression pC;

   public Dd(ICExpression var1) {
      this.pC = var1;
   }

   public ICExpression kS() {
      return this.pC;
   }

   @Override
   public Collection getPhysicalOffsets() {
      return this.pC.getPhysicalOffsets();
   }

   @Override
   public List getSubElements() {
      return afm.pC(this.pC);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      return this.pC.replaceSubElement(var1, var2);
   }

   @Override
   public Long getPhysicalOffset() {
      return this.pC.getPhysicalOffset();
   }

   @Override
   public void generate(COutputSink var1) {
      this.pC.generate(var1);
   }

   @Override
   public CElementType getElementType() {
      return this.pC.getElementType();
   }

   @Override
   public String toString() {
      return this.pC.toString();
   }

   @Override
   public String format() {
      return this.pC.format();
   }

   public Dd A() {
      return new Dd(this.pC.duplicate());
   }

   @Override
   public boolean visitDepthPost(ICVisitor var1) {
      return this.pC.visitDepthPost(var1);
   }

   @Override
   public boolean visitDepthPost(ICVisitor var1, ICElement var2) {
      return this.pC.visitDepthPost(var1, var2);
   }

   @Override
   public boolean visitDepthPost(ICVisitor var1, ICElement var2, CVisitResults var3) {
      return this.pC.visitDepthPost(var1, var2, var3);
   }

   @Override
   public boolean visitDepthPre(ICVisitor var1) {
      return this.pC.visitDepthPre(var1);
   }

   @Override
   public boolean visitDepthPre(ICVisitor var1, ICElement var2) {
      return this.pC.visitDepthPre(var1, var2);
   }

   @Override
   public boolean visitDepthPre(ICVisitor var1, ICElement var2, CVisitResults var3) {
      return this.pC.visitDepthPre(var1, var2, var3);
   }

   @Override
   public void setPhysicalOffsets(Collection var1) {
      this.pC.setPhysicalOffsets(var1);
   }

   @Override
   public void addPhysicalOffset(Long var1) {
      this.pC.addPhysicalOffset(var1);
   }

   @Override
   public void addPhysicalOffsets(Collection var1) {
      this.pC.addPhysicalOffsets(var1);
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      return this.pC.evaluate(var1, var2);
   }

   @Override
   public Object getData(Object var1) {
      return this.pC.getData(var1);
   }

   @Override
   public void setData(String var1, Object var2) {
      this.pC.setData(var1, var2);
   }
}
