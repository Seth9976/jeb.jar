package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface ICDecompilableElement extends ICSourceElement {
   int STATUS_NORMAL = 0;
   int STATUS_UNINITIALIZED = 1;
   int STATUS_ERROR = 2;
   int STATUS_LIMITED = 3;
   int STATUS_STALE = 4;

   String getAddress();

   void reset();

   boolean isExternal();

   int getIndex();

   int getFlags();

   void setStatus(int var1, String var2);

   void setStatusCode(int var1);

   int getStatusCode();

   String getStatusMessage();

   ICGlobalContext getGlobalContext();

   default ICFieldFactory getFieldFactory() {
      return this.getGlobalContext().getFieldFactory();
   }

   default ICMethodFactory getMethodFactory() {
      return this.getGlobalContext().getMethodFactory();
   }

   default ICClassFactory getClassFactory() {
      return this.getGlobalContext().getClassFactory();
   }

   default ICTypeFactory getTypeFactory() {
      return this.getGlobalContext().getTypeFactory();
   }

   default ICConstantFactory getConstantFactory() {
      return this.getGlobalContext().getConstantFactory();
   }

   default ICOperatorFactory getOperatorFactory() {
      return this.getGlobalContext().getOperatorFactory();
   }

   default ICElementFactory getElementFactory() {
      return this.getGlobalContext().getElementFactory();
   }

   boolean addDecompilationNote(String var1);

   List getDecompilationNotes();

   ICDecompilableElement duplicate();
}
