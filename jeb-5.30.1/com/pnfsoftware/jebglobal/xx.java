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
public class xx implements ICExpression {
   @SerId(1)
   private ICExpression q;

   public xx(ICExpression var1) {
      this.q = var1;
   }

   public ICExpression xK() {
      return this.q;
   }

   @Override
   public Collection getPhysicalOffsets() {
      return this.q.getPhysicalOffsets();
   }

   @Override
   public List getSubElements() {
      return ahf.q(this.q);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      return this.q.replaceSubElement(var1, var2);
   }

   @Override
   public Long getPhysicalOffset() {
      return this.q.getPhysicalOffset();
   }

   @Override
   public void generate(COutputSink var1) {
      this.q.generate(var1);
   }

   @Override
   public CElementType getElementType() {
      return this.q.getElementType();
   }

   @Override
   public String toString() {
      return this.q.toString();
   }

   @Override
   public String format() {
      return this.q.format();
   }

   public xx RF() {
      return new xx(this.q.duplicate());
   }

   @Override
   public boolean visitDepthPost(ICVisitor var1) {
      return this.q.visitDepthPost(var1);
   }

   @Override
   public boolean visitDepthPost(ICVisitor var1, ICElement var2) {
      return this.q.visitDepthPost(var1, var2);
   }

   @Override
   public boolean visitDepthPost(ICVisitor var1, ICElement var2, CVisitResults var3) {
      return this.q.visitDepthPost(var1, var2, var3);
   }

   @Override
   public boolean visitDepthPre(ICVisitor var1) {
      return this.q.visitDepthPre(var1);
   }

   @Override
   public boolean visitDepthPre(ICVisitor var1, ICElement var2) {
      return this.q.visitDepthPre(var1, var2);
   }

   @Override
   public boolean visitDepthPre(ICVisitor var1, ICElement var2, CVisitResults var3) {
      return this.q.visitDepthPre(var1, var2, var3);
   }

   @Override
   public void setPhysicalOffsets(Collection var1) {
      this.q.setPhysicalOffsets(var1);
   }

   @Override
   public void addPhysicalOffset(Long var1) {
      this.q.addPhysicalOffset(var1);
   }

   @Override
   public void addPhysicalOffsets(Collection var1) {
      this.q.addPhysicalOffsets(var1);
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      return this.q.evaluate(var1, var2);
   }

   @Override
   public Object getData(Object var1) {
      return this.q.getData(var1);
   }

   @Override
   public void setData(String var1, Object var2) {
      this.q.setData(var1, var2);
   }
}
