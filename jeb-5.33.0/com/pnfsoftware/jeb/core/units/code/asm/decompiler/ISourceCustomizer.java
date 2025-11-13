package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstant;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCustomStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICField;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;

public interface ISourceCustomizer {
   boolean generateClassDeclarationLine(ICClass var1, COutputSink var2);

   boolean preFieldsGeneration(ICClass var1, COutputSink var2);

   boolean preMethodsGeneration(ICClass var1, COutputSink var2);

   boolean generateFieldDeclarationLine(ICField var1, COutputSink var2);

   boolean generateMethodDeclarationLine(ICMethod var1, COutputSink var2);

   boolean generateOperation(ICOperation var1, COutputSink var2);

   boolean generateNativeStatement(ICCustomStatement var1, COutputSink var2);

   String customizeRenderedConstant(ICConstant var1, String var2);
}
