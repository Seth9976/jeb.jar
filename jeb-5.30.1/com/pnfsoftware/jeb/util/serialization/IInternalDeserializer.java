package com.pnfsoftware.jeb.util.serialization;

import com.pnfsoftware.jeb.util.base.IProgressCallback;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

interface IInternalDeserializer {
   void setExpectedObjectCount(int var1);

   int getObjectCount();

   Collection getObjects();

   InputStream getStream();

   Object deserializeInternal() throws IOException;

   Object read() throws IOException;

   void restoreFields(Object var1, Class var2) throws IOException;

   void addProgressCallback(IProgressCallback var1);

   void removeProgressCallback(IProgressCallback var1);

   void addObjectCreatedHook(Class var1, IDeserializationEventHandler var2);

   void removeObjectCreatedHook(Class var1, IDeserializationEventHandler var2);
}
