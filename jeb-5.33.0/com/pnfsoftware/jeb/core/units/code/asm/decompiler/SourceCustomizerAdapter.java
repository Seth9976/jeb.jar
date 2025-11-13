package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstant;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCustomStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICField;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;

public class SourceCustomizerAdapter implements ISourceCustomizer {
   @Override
   public boolean generateClassDeclarationLine(ICClass var1, COutputSink var2) {
      return false;
   }

   @Override
   public boolean preFieldsGeneration(ICClass var1, COutputSink var2) {
      return false;
   }

   @Override
   public boolean preMethodsGeneration(ICClass var1, COutputSink var2) {
      return false;
   }

   @Override
   public boolean generateFieldDeclarationLine(ICField var1, COutputSink var2) {
      return false;
   }

   @Override
   public boolean generateMethodDeclarationLine(ICMethod var1, COutputSink var2) {
      return false;
   }

   @Override
   public boolean generateOperation(ICOperation var1, COutputSink var2) {
      return false;
   }

   @Override
   public boolean generateNativeStatement(ICCustomStatement var1, COutputSink var2) {
      return false;
   }

   @Override
   public String customizeRenderedConstant(ICConstant var1, String var2) {
      return null;
   }
}
