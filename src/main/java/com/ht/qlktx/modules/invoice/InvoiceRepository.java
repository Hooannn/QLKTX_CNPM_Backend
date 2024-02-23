package com.ht.qlktx.modules.invoice;

import com.ht.qlktx.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
