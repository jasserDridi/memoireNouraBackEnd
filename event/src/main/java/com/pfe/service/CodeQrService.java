package com.pfe.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.pfe.client.UploadClient;
import com.pfe.domain.entity.CodeQrResponse;
import com.pfe.domain.entity.Event;
import com.pfe.domain.entity.QrCode;
import com.pfe.domain.entity.QrCodeRequest;
import com.pfe.repository.CodeQrRespository;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ApplicationScoped
public class CodeQrService {

    @Inject
    @RestClient
    static
    UploadClient uploadClient;
    @Inject
    UploadImageService service;
    @Inject
    EventService eventService;

/*    public Uni<QrCode> getQRCode(QrCodeRequest qrCodeRequest)
            throws  IOException, WriterException {
        int width=500; int height=500;
        String description ="user Noura "+"has a ticket for event "+qrCodeRequest.getEvent().getNom()+" at"+qrCodeRequest.getEvent().getLieu()+" from "+qrCodeRequest.getEvent().getStartDate()+" to "+qrCodeRequest.getEvent().getEndDate();
        String imageFormat = "png"; // could be "gif", "tiff", "jpeg"
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.
                encode(description, BarcodeFormat.QR_CODE, width, height);
        ByteArrayOutputStream pngOutputStream =
                new ByteArrayOutputStream();
        MatrixToImageWriter.
                writeToStream(bitMatrix, imageFormat, pngOutputStream);
        log.info("this is the qr code "+pngOutputStream.toByteArray());

        ByteArrayInputStream code=  new ByteArrayInputStream(pngOutputStream.toByteArray());

            return this.codeQrRespository.persist(qr);

        }*/


    public Uni<CodeQrResponse> generateQRCode(QrCodeRequest qrCodeRequest) throws WriterException, IOException {

       return    eventService.findOne(qrCodeRequest.getEventId())
                    .chain(event -> {


            String data = "Noura achète un billet pour assister a "+event.getNom()+ " at "+event.getLieu()+" "+" , date debut "+event.getStartDate()+", fin date "+event.getEndDate(); // The data you want to encode in the QR code
            int width = 300; // Width of the QR code image
            int height = 300; // Height of the QR code image
            String format = "png"; // Image format (e.g., "png", "jpeg", "gif")

            Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
                        BitMatrix bitMatrix = null;
                        try {
                            bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height, hintMap);
                        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrImage, format, baos);
            baos.flush();
            byte[] imageData = baos.toByteArray();
            baos.close();

                            return Uni.createFrom().item(new CodeQrResponse(service.uploadPhoto(imageData).get("url").toString()));
                        } catch (IOException | WriterException e) {
                            throw new RuntimeException(e);
                        }
                    });
    }
    }





