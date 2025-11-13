package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.IOptimizerTarget;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface ICMethod extends ICDecompilableElement, IOptimizerTarget {
   ICIdentifierManager getIdentifierManager();

   ICLabelFactory getLabelFactory();

   List getParameters();

   void addParameter(ICDecl var1);

   void addParameter(int var1, ICDecl var2);

   void removeParameter(int var1);

   List getReturnTypes();

   ICType getReturnType();

   ICType getClassType();

   String getName();

   ICBlock getBody();

   boolean isEmpty();

   void generateName(COutputSink var1, boolean var2);

   List getStatements();

   void addStatement(ICStatement var1);

   ICStatement getLastStatement();

   boolean deleteStatement(ICStatement var1);

   boolean insertAtOffset(long var1, ICStatement var3);

   boolean visitStatements(ICVisitor var1);

   List toFlatList();

   void fromFlatList(List var1);

   ICMethod duplicate();
}
