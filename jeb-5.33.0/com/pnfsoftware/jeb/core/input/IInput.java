package com.pnfsoftware.jeb.core.input;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

@Ser
public interface IInput {
   int IDEAL_HEADER_SIZE = 512;

   String getName();

   boolean canRead();

   void close();

   long getCurrentSize();

   ByteBuffer getHeader();

   SeekableByteChannel getChannel() throws IOException;

   InputStream getStream() throws IOException;
}
