CREATE TABLE refresh_token(
    id TEXT NOT NULL,
    expires_in TEXT NOT NULL,
    user_id TEXT NOT NULL,

    created_at TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP(3) DEFAULT NULL,

    CONSTRAINT "refresh_token_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "fk_user_refresh_token" FOREIGN KEY ("user_id") REFERENCES users("id")
)