package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICGlobalContext {
   ICNamingEngine getNamingEngine();

   ICClassFactory getClassFactory();

   ICFieldFactory getFieldFactory();

   ICMethodFactory getMethodFactory();

   ICTypeFactory getTypeFactory();

   ICIdentifierManager getIdentifierManager();

   ICConstantFactory getConstantFactory();

   ICOperatorFactory getOperatorFactory();

   ICElementFactory getElementFactory();

   ICIdentifierManager createLocalIdentifierManager();

   ICLabelFactory createLabelFactory();
}
