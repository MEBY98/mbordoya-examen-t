package com.mercadona.mbordoya.web.main.application.utils;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public abstract class CsvWriter <T extends Exportable> {

    protected List<String> headers;

    public byte[] parseToBytesWithHeaders(List<T> items) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream(); OutputStreamWriter writer =
                new OutputStreamWriter(out, StandardCharsets.UTF_8); BufferedWriter bw = new BufferedWriter(writer)) {
            writer.write("\uFEFF");
            writeHeaders(bw);
            writeRows(items, bw);
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] parseToBytes(List<T> items) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream(); OutputStreamWriter writer =
                new OutputStreamWriter(out, StandardCharsets.UTF_8); BufferedWriter bw = new BufferedWriter(writer)) {
            writeRows(items, bw);
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeRows(List<T> items, BufferedWriter bw) throws IOException {
        for (T item : items) {
            writeLine(item, bw);
        }
        bw.flush();
    }

    private void writeLine(T item, BufferedWriter bw) throws IOException {
        String line = String.join(";", item.getRow());
        bw.write(line);
        bw.newLine();
    }

    private void writeHeaders(BufferedWriter bw) throws IOException {
        bw.write(String.join(";", headers));
        bw.newLine();
    }
}