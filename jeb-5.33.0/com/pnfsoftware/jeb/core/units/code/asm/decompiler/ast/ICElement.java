package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.List;

@Ser
public interface ICElement {
   List getSubElements();

   boolean replaceSubElement(ICElement var1, ICElement var2);

   Long getPhysicalOffset();

   Collection getPhysicalOffsets();

   void setPhysicalOffsets(Collection var1);

   void addPhysicalOffset(Long var1);

   void addPhysicalOffsets(Collection var1);

   void generate(COutputSink var1);

   CElementType getElementType();

   @Override
   String toString();

   String format();

   ICElement duplicate();

   boolean visitDepthPost(ICVisitor var1);

   boolean visitDepthPost(ICVisitor var1, ICElement var2);

   boolean visitDepthPost(ICVisitor var1, ICElement var2, CVisitResults var3);

   boolean visitDepthPre(ICVisitor var1);

   boolean visitDepthPre(ICVisitor var1, ICElement var2);

   boolean visitDepthPre(ICVisitor var1, ICElement var2, CVisitResults var3);

   Long evaluate(CMethodState var1, CEnvironment var2);

   void setData(String var1, Object var2);

   Object getData(Object var1);
}
