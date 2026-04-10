package edu.eci.dosw.controller;

import edu.eci.dosw.dto.LoanDTO;
import edu.eci.dosw.persistence.document.LoanDocument;
import edu.eci.dosw.service.LoanService;
import edu.eci.dosw.util.ValidationUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PreAuthorize("hasAnyRole('USER','LIBRARIAN')")
    @PostMapping
    public LoanDocument loanBook(@RequestBody LoanDTO dto) {
        ValidationUtil.notBlank(dto.bookId, "Book id is required");
        ValidationUtil.notBlank(dto.userId, "User id is required");

        return loanService.loanBook(dto.bookId, dto.userId);
    }

    @PreAuthorize("hasAnyRole('USER','LIBRARIAN')")
    @GetMapping
    public List<LoanDocument> getLoans() {
        return loanService.getLoans();
    }
}